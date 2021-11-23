package com.crilofer.core.exception;

import java.io.Serializable;

public class ExternalServiceException extends BaseException implements Serializable {

    public ExternalServiceException(String msg, Integer status) {
        super(msg, status);
    }
}
