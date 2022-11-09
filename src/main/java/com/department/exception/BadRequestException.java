package com.department.exception;

import java.io.Serial;

/**
 * @author Vladimir F. 13.09.2022 15:21
 */
 
public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;

    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
