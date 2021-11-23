package com.crilofer.core.exception;

import com.crilofer.controller.dto.response.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class ExceptionMapperWrapper {

    private ExceptionMapperWrapper() {
    }

    @Provider
    public static class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

        private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingExceptionMapper.class.getName());

        @Override
        public Response toResponse(ProcessingException exception) {
            LOGGER.error("There were an internal server error. Error: {}", exception.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new MessageDto(
                    Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Internal Server error")).build();
        }
    }

    @Provider
    public static class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

        private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundExceptionMapper.class.getName());

        @Override
        public Response toResponse(NotFoundException exception) {
            LOGGER.error("There were an internal server error. Error: {}", exception.getMessage());

            return Response.status(exception.getResponse().getStatus()).entity(new MessageDto(
                    exception.getResponse().getStatus(), exception.getMessage())).build();
        }
    }
}
