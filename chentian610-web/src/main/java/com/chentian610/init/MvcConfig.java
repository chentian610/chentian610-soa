package com.chentian610.init;

import com.chentian610.framework.DataInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册数据初始化拦截器
        InterceptorRegistration ir = registry.addInterceptor(new DataInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/");
        // 配置不拦截的路径
//        ir.excludePathPatterns("/**.html");
//        ir.excludePathPatterns("/**.css");
//        ir.excludePathPatterns("/**.html");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }

}
