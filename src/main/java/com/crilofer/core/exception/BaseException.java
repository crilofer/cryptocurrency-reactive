package com.crilofer.core.exception;

import java.io.Serializable;

public abstract class BaseException extends Exception implements Serializable {

    private final Integer exceptionCode;

    protected BaseException(String msg, Integer exceptionCode) {
        super(msg);
        this.exceptionCode = exceptionCode;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }
}