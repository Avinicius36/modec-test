package com.modecbackend.modecapplication.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ApiRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApiRequestException(String message) {
        super(message);

    }

    public ApiRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
