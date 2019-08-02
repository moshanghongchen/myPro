package com.lch.my_air_pan.model.user.serviceImpl;

import com.lch.my_air_pan.model.user.dao.UserMapper;
import com.lch.my_air_pan.model.user.entity.User;
import com.lch.my_air_pan.model.user.entity.UserExample;
import com.lch.my_air_pan.model.user.service.UserService;
import com.lch.my_air_pan.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;


    @Override
    public List<User> showAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public boolean login(User user, HttpServletRequest request)throws Exception {
        final var userExample = new UserExample();
        userExample.createCriteria().andPasswordEqualTo(user.getPassword()).andUserNameEqualTo(user.getUserName());
        final var users = userMapper.selectByExample(userExample);
        if(users==null||users.size()==0){
            log.error("登陆失败,登陆信息：{}",user);
            return false;
        }
        request.getSession().setAttribute("user",users.get(0));
        final boolean user1 = redisService.save("user", users.get(0).getId());
        if(!user1){
            log.info("存redis失败");
        }
        return true;
    }

    /**
     * @Description: UserService
     * @Param: 获取当前用户id
     * @return:
     * @Author: LiuCanHui
     * @Date: 16:38
     */
    @Override
    public String getCurrentUserId() throws Exception {
        return redisService.get("user");
    }


}
