package com.chentian610.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 普通类调用Spring bean对象：
 * @author chenth
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    protected static Logger _logger = LoggerFactory.getLogger(SpringBeanUtil.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext == null){
            SpringBeanUtil.applicationContext  = applicationContext;
        }
        _logger.debug("初始化SpringUtil容器成功...");
    }


    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }



    /**
     * 通过name获取 Bean.
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }



    /**
     * 通过CLASS获取 Bean.
     * @return
     */
    public static <T> T getBean(Class<T> clazz){

        return getApplicationContext().getBean(clazz);

    }



    /**
     * 通过name,以及Clazz返回指定的Bean
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}