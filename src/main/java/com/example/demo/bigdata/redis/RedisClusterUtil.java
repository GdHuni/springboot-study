package com.example.demo.bigdata.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Administrator
 */
@Component
public class RedisClusterUtil {
    @Value("${redis.nodes}")
    private String clusterNodes;

    public JedisCluster getJedisCluster() {
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(10);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(10);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000);

        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            jedisClusterNode.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        return new JedisCluster(jedisClusterNode, jedisPoolConfig);
    }

}
