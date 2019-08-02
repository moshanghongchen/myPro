package com.lch.my_air_pan.model.file.controller;

import com.lch.my_air_pan.model.file.entity.FileBean;
import com.lch.my_air_pan.model.file.service.MongoService;
import com.lch.my_air_pan.model.user.service.UserService;
import com.lch.my_air_pan.model.user.serviceImpl.UserServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("fileController")
/**
 * @Description: FileController
 * @Author: LiuCanHui
 * @Date: 2019/6/6
 */
public class FileController {
    @Autowired
    private MongoService mongoService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * @Description: FileController
     * @Param: [fileBean]
     * @return: boolean
     * @Author: LiuCanHui
     * @Date: 18:03
     */
    @RequestMapping("upLoadFile")
    public boolean upLoadFile(FileBean fileBean) throws Exception{
        mongoService.saveFile(fileBean);
        return true;
    }
    @ResponseBody
    @RequestMapping("showFile")
    public List<FileBean> downLoadFile() throws Exception{
        final HashMap<String, BsonString> map = new HashMap<>(10);
        map.put("metadata.ownerId",new BsonString(userService.getCurrentUserId()));
        final List<FileBean> beans = mongoService.queryFileByDocument(map);
        return beans;
    }
    @ResponseBody
    @RequestMapping("showFileByBootStrap")
    public Map <String,Object> showFileByBootStrap() throws Exception{
        final HashMap<String, BsonString> map = new HashMap<>();
        map.put("metadata.ownerId",new BsonString(userService.getCurrentUserId()));
        final List<FileBean> beans = mongoService.queryFileByDocument(map);
        return getBootStrapData(beans,40);
    }
    @ResponseBody
    @RequestMapping("downloadFile")
    public String downloadFile(String fileId, HttpServletResponse response) throws Exception{
        //获取文件名
        final HashMap<String, Object> map = new HashMap<>();
        map.put("_id",new ObjectId(fileId));
        final List<FileBean> fileBeans = mongoService.queryFileByDbFilter(map);
        final String fileName = fileBeans.get(0).getFileName();
        //设置响应方式
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));

        //获取文件资源
        final boolean loadFile = mongoService.downLoadFile(response, fileId);


        if (loadFile){
            return "success";
        }else{
            throw new RuntimeException("获取文件资源失败");
        }
    }


    public Map <String,Object> getBootStrapData(List<?> list,int count){
        final HashMap<String, Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

}
