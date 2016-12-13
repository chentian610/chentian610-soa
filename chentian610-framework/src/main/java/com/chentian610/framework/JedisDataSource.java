package com.chentian610.framework;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

public interface JedisDataSource {

	public ShardedJedis getRedisClient();

	public void returnResource(ShardedJedis shardedJedis, boolean broken);

	public void returnResource(ShardedJedis shardedJedis);
	
	public Jedis getJedisClient();
	
	public void returnResource(Jedis jedis);
	
	public void returnResource(Jedis jedis, boolean broken);

}
