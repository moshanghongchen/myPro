package com.lch.my_air_pan.model.file.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Data
@Slf4j
public class FileBean {
    private String id;
    private String fileName;
    private MultipartFile file;
    private long fileSize;
    private String fileType;
    private long updateTime;
    public static final  String PICTURE="图片";
    public static final  String VIDEO="视频";
    public static final  String FILE="文件";
    {
        log.info("");
    }
}
