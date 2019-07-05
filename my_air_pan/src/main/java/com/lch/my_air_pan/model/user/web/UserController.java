package com.lch.my_air_pan.model.user.web;

import com.lch.my_air_pan.model.user.entity.User;
import com.lch.my_air_pan.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userController")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("showAll")
    public List<User> showAll(){
        List<User> users = userService.showAll();
        return users;
    }
}
