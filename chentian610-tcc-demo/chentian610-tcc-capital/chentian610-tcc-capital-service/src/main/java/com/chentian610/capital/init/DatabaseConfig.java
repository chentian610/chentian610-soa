package com.chentian610.capital.init;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@PropertySource({"classpath:jdbc.properties"})
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${jdbc.driverClassName}")
    private String jdbcDriver;

    @Value("${jdbc.databaseURL}")
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

    /**
     * 描述 : 初始化配置文件必须写的方法，否则获取不到配置文件值
     * @return
     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
//        logger.info("初始化配置文件......");
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        logger.info("课道平台数据库连接池初始化开始：URL:"+dbUrl);
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
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public TransactionInterceptor initTransactionInterceptor() {
        logger.info("课道平台initTranscationInterceptor.................");
        PlatformTransactionManager txManager = txManager();
        Properties properties = new Properties();
        properties.put("*","PROPAGATION_REQUIRED");
        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute all = new RuleBasedTransactionAttribute();
        all.setPropagationBehavior(TransactionAttribute.PROPAGATION_REQUIRED);
        tas.addTransactionalMethod("*", all);
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(txManager,tas);
        return transactionInterceptor;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.info("课道平台MyBatis初始化开始.................");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setConfigLocation(new ClassPathResource("mybatisframework.xml"));
        sessionFactory.setTypeAliasesPackage("com.ninesky");
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:com/chentian610/**/config/*.xml");
        logger.info("课道平台MyBatis成功加载Mapper文件数量："+resources.length);
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}