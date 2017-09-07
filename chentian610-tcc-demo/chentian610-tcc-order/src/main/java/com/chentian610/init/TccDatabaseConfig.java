package com.chentian610.init;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.remoting.TimeoutException;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@EnableTransactionManagement
@Configuration
@PropertySource({"classpath:jdbc.properties"})
@ImportResource(locations={"classpath:tcc-transaction.xml","classpath:tcc-transaction-dubbo.xml"})
public class TccDatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(TccDatabaseConfig.class);

    @Value("${jdbc.driverClassName}")
    private String jdbcDriver;

    @Value("${tcc.jdbc.databaseURL}")
    private String dbUrl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 最小连接池数量
     */
    @Value("${jdbc.minIdle}")
    private Integer minIdle;

    /**
     * 最小连接池数量
     */
    @Value("${jdbc.maxIdle}")
    private Integer maxIdle;

    /**
     * 最大连接池数量，默认8
     */
    @Value("${jdbc.maxActive}")
    private Integer maxActive;

    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，
     * 并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    @Value("${jdbc.maxWait}")
    private Integer maxWait;


    @Value("${jdbc.validationQuery}")
    private String validationQuery;


    @Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;


    @Value("${tcc.domain}")
    private String tccDomain;

    @Value("${tcc.tbSuffix}")
    private String tccTbSuffix;


    public DataSource dataSource() {
        logger.info("课道平台tcc数据库连接池初始化开始：URL:"+dbUrl);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        return dataSource;
    }


    @Bean
    public DefaultRecoverConfig initDefaultRecoverConfig(){
        DefaultRecoverConfig defaultRecoverConfig = new DefaultRecoverConfig();
        defaultRecoverConfig.setMaxRetryCount(30);
        defaultRecoverConfig.setRecoverDuration(60);
        defaultRecoverConfig.setCronExpression("0/30 * * * * ?");
        Set<Class<? extends Exception>> delayCancelExceptions =  new HashSet<Class<? extends Exception>>();
        delayCancelExceptions.add(TimeoutException.class);
        defaultRecoverConfig.setDelayCancelExceptions(delayCancelExceptions);
        return defaultRecoverConfig;
    }

    @Bean
    public SpringJdbcTransactionRepository initSpringJdbcTransactionRepository() throws Exception {
        SpringJdbcTransactionRepository springJdbcTransactionRepository =  new SpringJdbcTransactionRepository();
        springJdbcTransactionRepository.setDataSource(dataSource());
        springJdbcTransactionRepository.setDomain(tccDomain);
        springJdbcTransactionRepository.setTbSuffix(tccTbSuffix);
        return springJdbcTransactionRepository;
    }
}