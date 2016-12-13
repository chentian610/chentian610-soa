package com.chentian610.chentian610.getui.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:redis.properties"})
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.timeout}")
    private Integer timeout;

    @Value("${redis.password}")
    private String password;

    /**
     * 最大连接量
     */
    @Value("${redis.pool.maxActive}")
    private Integer maxActive;

    /**
     * 最大连接池数量
     */
    @Value("${redis.pool.maxIdle}")
    private Integer maxIdle;

    /**
     * 最小连接池数量
     */
    @Value("${redis.pool.minIdle}")
    private Integer minIdle;

    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，
     * 并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    @Value("${redis.pool.maxWait}")
    private Integer maxWait;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.pool.testOnReturn}")
    private boolean testOnReturn;

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool() {
        logger.info("课道平台JedisPool Bean初始化");
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(),host,port,timeout,password);
        return jedisPool;
    }
}