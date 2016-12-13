package com.chentian610.framework;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import redis.clients.jedis.ShardedJedis;


public interface JedisDAO {

    /**
     * 设置单个值
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 获取某个集合的所有成员
     * @param key
     * @return
     */
    public Set<String> sMembers(String key);

    /**
     * 获取单个值
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 对某个Key增长1值
     * @param key 被加数的key
     * @return
     */
    public long incr(String key);

    /**
     * 对某个Key增长value值
     * @param key 被加数的key
     * @param value 加数
     * @return
     */
    public long incrBy(String key, long value);

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key);

    /**
     * 判断key是否不存在
     * @param key
     * @return
     */
    public Boolean notExists(String key);

    public String type(String key);

    /**
     * 在某段时间后失效
     * 
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds);
    
    /**
     * 在某个时间点失效
     * @param key 待处理的key
     * @param date 失效时间点
     * @return
     */
    public Long expireAt(String key, Date date);

    /**
     * 在某个时间点失效
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime);

    public Long ttl(String key);

    public boolean setbit(String key, long offset, boolean value);

    public boolean getbit(String key, long offset);

    public long setRange(String key, long offset, String value);

    public String getRange(String key, long startOffset, long endOffset);
    
    /**
     * 判断hash中该key下有没有该field
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field);
    
    /**
     * 向hash中加入字段值
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hset(String key, String field, String value);
    
    /**
     * 获取hash中的字段值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field);
    
    /**
     * 获取hash中的所有值
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key);
    
    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment 
     */
    public long hincrBy(String key, String field, long value);
    
    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值
     */
    public long hsetnx(String key, String field, String value);
    
    /**
     * 获取哈希表中所有值
     */
    public List<String> hvals(String key);
    
    /**
     * 有序集合添加元素
     * @param key 有序集合
     * @param score 分值
     * @param value 成员
     * @return
     */
    public long zadd(String key, double score, String value);
    
    /**
     * 有序集合指定分数区间的成员数量。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zcount(String key, double min, double max);
    
    /**
     * 获取有序集合的成员数
     */
    public long zcard(String key);
    
    /**
     * 移除有序集合中的一个或多个成员
     */
    public long zrem(String key, String members);
    
    /**
     * 通过索引区间返回有序集合成指定区间内的成员,按分数值递增(从小到大)来排序。
     */
    public Set<String> zrange(String key, long start, long end);
    
    /**
     * 通过索引区间返回有序集合成指定区间内的成员,按分数值递增(从大到小)来排序。
     */
    public Set<String> zrevrange(String key, long start, long end);

    /**
     * 确定一个有序集合成员的索引，以分数排序，从低分到高分
     * @param key 集合key
     * @param member_key 成员key
     */
    public long zrank(String key, String member_key);

    /**
     * 确定一个有序集合成员的索引，以分数排序，从高分到低分
     * @param key 集合key
     * @param member_key 成员key
     */
    public long zrevrank(String key, String member_key);
    
    /**
     * 返回有序集中，成员的分数值
     */
    public Double zscore(String key, String member);
    
    /**
     * 将一个或多个值插入到列表头部
     * @return
     */
    public long lpush(String key, String string);
    
    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * @return
     */
    public String ltrim(String key, long start, long end);
    /**
     * 通过索引获取列表中的元素
     * @return
     */
    public List<String> lrange(String key, long start, long end);
    
    /**
     * 向集合添加一个或多个成员
     */
    public long sadd(String key, String string);
    
    /**
     * 移除集合中一个或多个成员
     */
    public long srem(String key, String string);
    
    /**
     * 该命令用于在 key 存在时删除 key
     */
    public long del(String key);
    
    /**
     * 模糊查询redis中的key   
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern);
    
    public long zUnionStore(String zKey, String zOherKey, String zDestKey);
    
    /**
     * 设置hashMap到key中
     * @param key 
     * @param map
     * @return
     */
    public void hsetAll(String key, Map<String, String> map);

}