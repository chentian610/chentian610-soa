package com.chentian610.framework.impl;

import com.chentian610.framework.JedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("jedisDS") 
public class JedisDataSourceImpl implements JedisDataSource {
    private static final Logger LOG = LoggerFactory.getLogger(JedisDataSourceImpl.class);

//    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private JedisPool jedisPool;
    
    @Override
    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
        	e.printStackTrace();
            LOG.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != shardJedis)
                shardJedis.close();
        }
        return null;
    }
    
    /**
     * 从jedis连接池中获取获取jedis对象
     * 
     * @return
     */
    @Override
    public Jedis getJedisClient() {
        return jedisPool.getResource();
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        shardedJedis.close();
    }
    
    @Override
    public void returnResource(Jedis jedis) {
    	jedis.close();
    }
    
    @Override
    public void returnResource(Jedis jedis, boolean broken) {
    	jedis.close();
    }

}