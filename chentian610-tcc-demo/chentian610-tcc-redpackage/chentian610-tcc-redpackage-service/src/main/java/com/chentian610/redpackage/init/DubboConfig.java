package com.chentian610.redpackage.init;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by xin on 15/12/27.
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
public class DubboConfig {

    @Value("${dubbo.application.name}")
    private String dubbo_application_name;


    @Value("${dubbo.registry.address}")
    private String dubbo_registry_address;

    /**
     * 由《dubbo:application》转换过来
     **/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(dubbo_application_name);
        return applicationConfig;
    }

    /**
    * 与<dubbo:annotation/>相当.提供方扫描带有@com.alibaba.dubbo.init.annotation.Reference的注解类
    * */
    @Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage("com.chentian610");//多个包可使用英文逗号隔开
        return annotationBean;
    }

    /**
     * 与<dubbo:registry/>相当
     * */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubbo_registry_address);
        return registryConfig;
    }

    /**dubbo:protocol
     * 与<dubbo:protocol/>相当
     * */
    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig=new ProtocolConfig("dubbo");
        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        protocolConfig.setOptimizer("com.alibaba.dubbo.demo.SerializationOptimizerImpl");
        protocolConfig.setPort(20881);
        return protocolConfig;
    }

}
