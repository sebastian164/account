package com.devsu.account.infrastructure.handler;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}