package com.lch.my_air_pan.model.user.service;

import com.lch.my_air_pan.model.user.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: UserService
 * @Author: LiuCanHui
 * @Date: 2019/7/5
 */

@Service
public interface UserService {
    /** 
     * @Description: UserService 
     * @Param: 查询所有用户信息
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 16:20 
     */
    List<User> showAll();
    /** 
     * @Description: UserService 
     * @Param: User 用户信息
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 9:37 
     */
    boolean login(User user, HttpServletRequest request)throws Exception;

/** 
 * @Description: UserService 
 * @Param: 获取当前用户id
 * @return:  
 * @Author: LiuCanHui 
 * @Date: 16:38 
 */
    String getCurrentUserId()throws Exception;
}
