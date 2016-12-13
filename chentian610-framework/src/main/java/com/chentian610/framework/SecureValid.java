package com.chentian610.framework;


import java.lang.annotation.*;
  //DD 
/*
 * 校验签名合法性 自定义事务
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SecureValid {
    String desc() default "chentianhui身份和安全验证开始...";
}
