package com.lch.my_air_pan.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Description: RedisService
 * @Author: LiuCanHui redis服务（先用jedis实现）
 * @Date: 2019/7/29
 */
@Service
@Slf4j
public class RedisService {
    private static Jedis jedis = null;

/**
 * @Description: RedisService
 * @Param: 获取jedis  reconnCount :重连次数 waitTime 重连时间隔
 * @return:
 * @Author: LiuCanHui
 * @Date: 10:49
 */
    public boolean getJedis(int reconnCount,long waitTime)throws Exception{
        if(reconnCount<=0){
            System.err.println("redis重连失败");
            return false;

        }
        reconnCount--;
        try {
            jedis=new Jedis("192.144.182.15", 6379);
        } catch (Exception e) {
            System.err.println("重新连接redis!!!!");
            Thread.sleep(waitTime);
            getJedis(reconnCount,waitTime);
        }
        return true;
    }

    /** 
     * @Description: RedisService 
     * @Param: 保存
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 13:56 
     */
    public boolean save(String key,String value)throws Exception{
        getJedis(3,2000L);
        jedis.set(key,value);
        jedis.close();
        return true;
    }
    public String get(String key)throws Exception{
        getJedis(3,2000L);
        final String s = jedis.get(key);
        return s;
    }

}
