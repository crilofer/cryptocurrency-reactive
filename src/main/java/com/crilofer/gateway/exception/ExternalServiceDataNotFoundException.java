package com.crilofer.gateway.exception;

import com.crilofer.core.exception.ExternalServiceException;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ExternalServiceDataNotFoundException extends ExternalServiceException {

    protected ExternalServiceDataNotFoundException(String serviceName) {
        super(serviceName + " service not found", HttpResponseStatus.NOT_FOUND.code());
    }
}
