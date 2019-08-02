package com.lch.my_air_pan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * @Description: MyAirPanApplication 
 * @Param:
 * @return:  
 * @Author: LiuCanHui 
 * @Date: 20:10
 */
@SpringBootApplication
@MapperScan("com.lch.my_air_pan.model")
public class MyAirPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAirPanApplication.class, args);
    }
}
