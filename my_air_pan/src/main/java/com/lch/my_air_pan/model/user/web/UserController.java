package com.lch.my_air_pan.model.user.web;

import com.lch.my_air_pan.model.user.entity.User;
import com.lch.my_air_pan.model.user.service.UserService;
import com.lch.my_air_pan.model.user.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("userController")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @ResponseBody
    @RequestMapping("showAll")
    public List<User> showAll(){
        List<User> users = userService.showAll();
        return users;
    }

    @RequestMapping("login")
    public ModelAndView login(User user, HttpServletRequest request, ModelAndView mv){
        boolean login=true;
        try {
            login = userService.login(user, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
       if(login){
            mv.setViewName("index");
        }else{
           mv.setViewName("login");
       }
        return mv;
    }
}
