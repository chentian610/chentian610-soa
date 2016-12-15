package com.chentian610.framework;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
