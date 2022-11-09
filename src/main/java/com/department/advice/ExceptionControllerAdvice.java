package com.department.advice;

import com.department.dto.response.ErrorDetailsResponse;
import com.department.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vladimir F. 20.09.2022 11:10
 */

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailsResponse> badRequestExceptionHandler(BadRequestException exception) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(errorDetailsResponse);
    }
}
