package com.lch.my_air_pan.model.file.controller;

import com.lch.my_air_pan.model.file.entity.FileBean;
//import com.lch.my_air_pan.model.file.service.MongoService;
import com.lch.my_air_pan.model.file.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fileController")
/**
 * @Description: FileController
 * @Author: LiuCanHui
 * @Date: 2019/6/6
 */
public class FileController {
    @Autowired
    MongoService mongoService;

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
}
