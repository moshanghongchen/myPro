package com.lch.my_air_pan.model.user.service;

import com.lch.my_air_pan.model.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserService
 * @Author: LiuCanHui
 * @Date: 2019/7/5
 */

public interface UserService {
    /** 
     * @Description: UserService 
     * @Param: 查询所有用户信息
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 16:20 
     */
    List<User> showAll();
}
