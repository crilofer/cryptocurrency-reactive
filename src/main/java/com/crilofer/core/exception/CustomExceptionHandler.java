package com.crilofer.core.exception;

import com.crilofer.controller.dto.response.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<BaseException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class.getName());

    @Override
    public Response toResponse(BaseException exception) {
        LOGGER.error(exception.getMessage(), exception.getCause());

        return Response.status(exception.getExceptionCode()).entity(new MessageDto(exception.getExceptionCode()
                , exception.getMessage())).build();
    }
}