package com.lch.my_air_pan.model.user.serviceImpl;

import com.lch.my_air_pan.model.user.dao.UserMapper;
import com.lch.my_air_pan.model.user.entity.User;
import com.lch.my_air_pan.model.user.entity.UserExample;
import com.lch.my_air_pan.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> showAll() {
        return userMapper.selectByExample(new UserExample());
    }
}
