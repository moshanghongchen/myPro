package com.example.demoechars.model.web;

import com.example.demoechars.model.entity.EcharsTable;
import com.example.demoechars.model.service.EcharsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class EcharsTableController {
    @Autowired
    EcharsTableService echarsTableService;

/**
 * @Description: EcharsTableController
 * @Param: 按类型查询
 * @return:
 * @Author: LiuCanHui
 * @Date: 11:46
 */
    @RequestMapping("showAll")
    public ArrayList<EcharsTable> showAll(String type){
        return echarsTableService.getAll(type);
    }
/** 
 * @Description: EcharsTableController 
 * @Param: 保存体重信息 
 * @return:  
 * @Author: LiuCanHui 
 * @Date: 11:46 
 */
    @RequestMapping("save")
    public String save(EcharsTable echarsTable){
        int res=echarsTableService.save(echarsTable);
        if(res==1){
            return "success";
        }
        return "error";
    }

    /** 
     * @Description: EcharsTableController 
     * @Param: 查询所有信息
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 11:46 
     */
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(EcharsTable echarsTable){
        return echarsTableService.queryAll();
    }




}
