package com.crilofer.gateway.exception;

import com.crilofer.core.exception.ExternalServiceException;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;

public abstract class ExternalServiceExceptionMapper implements ResponseExceptionMapper<ExternalServiceException> {
    protected abstract String getServiceName();

    @Override
    public ExternalServiceException toThrowable(Response response) {
        if (response.hasEntity()) {
            return new ExternalServiceException(response.readEntity(String.class), response.getStatus());
        } else if (response.getStatus() == HttpResponseStatus.NOT_FOUND.code()) {
            return new ExternalServiceDataNotFoundException(getServiceName());
        } else {
            return new ExternalServiceException(getServiceName() + " service: "
                    + HttpResponseStatus.valueOf(response.getStatus()), response.getStatus());
        }
    }
}
