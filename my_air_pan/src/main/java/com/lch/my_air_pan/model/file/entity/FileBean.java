package com.lch.my_air_pan.model.file.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/** 
 * @Description: FileBean 
 * @Param: 文件对象
 * @return:  
 * @Author: LiuCanHui 
 * @Date: 17:17
 */
@Data
@Slf4j
public class FileBean implements Serializable {
    private String ownerId;
    private String id;
    private String fileName;
    private MultipartFile file;
    private Long fileSize;
    private String fileType;
    private Long updateTime;
    /**
        完整性校验
    */
    private String md5Code;
    public static final  String PICTURE="图片";
    public static final  String VIDEO="视频";
    public static final  String FILE="文件";

}
