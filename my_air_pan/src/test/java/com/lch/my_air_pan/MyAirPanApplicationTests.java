package com.lch.my_air_pan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAirPanApplicationTests {
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void contextLoads() {
        final Jedis jedis = new Jedis("192.144.182.15", 6379);
        jedis.set("user","123");
        jedis.close();
        System.out.println(jedis.get("user"));
    }

}
