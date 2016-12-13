package com.chentian610.framework.impl;

import com.chentian610.framework.JedisDAO;
import com.chentian610.framework.JedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;
import java.util.Map.Entry;


@Repository("jedisDAO")
public class JedisDAOImpl implements JedisDAO {

    private static final Logger log = LoggerFactory.getLogger(JedisDAOImpl.class);

    @Autowired
    private JedisDataSource jedisDataSource;

    /**
     * 设置单个值
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取某个集合的所有成员
     * @param key
     * @return
     */
    @Override
    public Set<String> sMembers(String key) {
        Set<String> result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.smembers(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 对某个Key增长1值
     *
     * @param key 被加数的key
     * @return
     */
    @Override
    public long incr(String key) {
        long result = 0;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.incr(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }


    /**
     * 对某个Key增长value值
     *
     * @param key   被加数的key
     * @param value 加数
     * @return
     */
    @Override
    public long incrBy(String key, long value) {
        long result = 0;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.incrBy(key,value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Boolean exists(String key) {
        Boolean result = false;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断key是否不存在
     *
     * @param key
     * @return
     */
    @Override
    public Boolean notExists(String key) {
        Boolean result = false;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = !shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String type(String key) {
        String result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某段时间后失效
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     * @param key 待处理的key
     * @param date 失效时间点
     * @return
     */
    public Long expireAt(String key, Date date) {
        return expireAt(key,date.getTime()/1000);
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Long ttl(String key) {
        Long result = null;
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public boolean setbit(String key, long offset, boolean value) {

        Jedis shardedJedis = jedisDataSource.getJedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setbit(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public boolean getbit(String key, long offset) {
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;

        try {
            result = shardedJedis.getbit(key, offset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public long setRange(String key, long offset, String value) {
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        long result = 0;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setrange(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String getRange(String key, long startOffset, long endOffset) {
        Jedis shardedJedis = jedisDataSource.getJedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.getrange(key, startOffset, endOffset);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 判断hash中该key下有没有该field
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key,String field){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        boolean result=false;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.hexists(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 向hash中加入字段值
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hset(String key,String field,String value){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) {
            log.error("INIT REDIS POOL FAILED.....................");
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hset(key, field, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取hash中的字段值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key,String field){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        String result=null;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        Map<String, String> result=null;
        if (shardedJedis==null) return null;
        boolean broken = false;
        try {
            result = shardedJedis.hgetAll(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     */
    public long hincrBy(String key,String field,long value){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.hincrBy(key, field, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值
     */
    public long hsetnx(String key,String field,String value){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.hsetnx(key, field, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取哈希表中所有值
     */
    public List<String> hvals(String key){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        List<String> result=null;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.hvals(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 有序集合添加元素
     * @param key
     * @param score
     * @param value
     * @return
     */
    public long zadd(String key,double score,String value){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.zadd(key, score, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 有序集合指定分数区间的成员数量。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zcount(String key,double min,double max){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.zcount(key, min, max);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取有序集合的成员数
     */
    public long zcard(String key){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.zcard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 移除有序集合中的一个或多个成员
     */
    public long zrem(String key,String members){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.zrem(key, members);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员,按分数值递增(从小到大)来排序。
     */
    public Set<String> zrange(String key,long start,long end){
        Jedis jedis = jedisDataSource.getJedisClient();
        Set<String> result = null;
        if (jedis == null) return result;
        ZParams param = new ZParams();
        param.aggregate(ZParams.Aggregate.MAX);
        try {
            result = jedis.zrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员,按分数值递增(从大到小)来排序。
     */
    public Set<String> zrevrange(String key,long start,long end){
        Jedis jedis = jedisDataSource.getJedisClient();
        Set<String> result = null;
        if (jedis == null) return result;
        try {
            result = jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    /**
     * 确定一个有序集合成员的索引，以分数排序，从低分到高分
     *
     * @param key        集合key
     * @param member_key 成员key
     */
    @Override
    public long zrank(String key, String member_key) {
        Jedis jedis = jedisDataSource.getJedisClient();
        long result = -1;
        if (jedis == null) return result;
        try {
            result = jedis.zrank(key, member_key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    /**
     * 确定一个有序集合成员的索引，以分数排序，从高分到低分
     *
     * @param key        集合key
     * @param member_key 成员key
     */
    @Override
    public long zrevrank(String key, String member_key) {
        Jedis jedis = jedisDataSource.getJedisClient();
        long result = -1;
        if (jedis == null) return result;
        try {
            result = jedis.zrevrank(key, member_key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    /**
     * 返回有序集中，成员的分数值
     */
    public Double zscore(String key,String member){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        Double result=0.0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.zscore(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 将一个或多个值插入到列表头部
     * @return
     */
    public long lpush(String key,String string){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.lpush(key, string);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * @return
     */
    public String ltrim(String key,long start,long end){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        String result=null;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.ltrim(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 通过索引获取列表中的元素
     * @return
     */
    public List<String> lrange(String key,long start,long end){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        List<String> result=null;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result = shardedJedis.lrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 向集合添加一个或多个成员
     */
    public long sadd(String key,String string){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.sadd(key, string);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 移除集合中一个或多个成员
     */
    public long srem(String key,String string){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.srem(key, string);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 该命令用于在 key 存在时删除 key
     */
    public long del(String key){
        Jedis shardedJedis= jedisDataSource.getJedisClient();
        long result=0;
        if (shardedJedis==null) return result;
        boolean broken = false;
        try {
            result=shardedJedis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 模糊查询redis中的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        Jedis jedis = jedisDataSource.getJedisClient();
        Set<String> result = null;
        if (jedis == null) return result;
        try {
            result = jedis.keys(pattern);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    public long zUnionStore(String zKey, String zOherKey, String zDestKey) {
        Jedis jedis = jedisDataSource.getJedisClient();
        long result = 0;
        if (jedis == null) return result;
        ZParams param = new ZParams();
        param.aggregate(ZParams.Aggregate.MAX);
        try {
            result = jedis.zunionstore(zDestKey,param,zKey,zOherKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置hashMap到key中
     * @param key 
     * @param map
     * @return
     */
	public void hsetAll(String key, Map<String, String> map) {
		 Jedis jedis = jedisDataSource.getJedisClient();
	     if (jedis == null) return;
        try {
            Iterator<Entry<String, String>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                jedis.hset(key, entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jedisDataSource.returnResource(jedis);
        }
	}
}