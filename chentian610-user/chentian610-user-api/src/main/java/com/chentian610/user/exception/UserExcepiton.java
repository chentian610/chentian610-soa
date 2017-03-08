package com.chentian610.user.exception;

import com.chentian610.framework.BusinessException;

/**
 * 用户模块自定义异常，防止消费端捕获不到异常
 * Created by com.chentian610 on 2016-12-16 .
 */
public class UserExcepiton extends BusinessException {
    public UserExcepiton(String message) {
        super(message);
    }
}
