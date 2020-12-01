package com.example.demo.bigdata.redis;

import com.example.demo.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

//启动Spring
public class RedisTest extends ApplicationTests{
    @Autowired
    RedisClusterUtil redisClusterUtil;
    @Test
    public void getJedisCluster() {
        JedisCluster jedisCluster = redisClusterUtil.getJedisCluster();
        jedisCluster.set("mykey","key");
        String name = jedisCluster.get("mykey");
        System.out.println(name);
    }

}