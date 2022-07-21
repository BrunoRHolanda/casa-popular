package com.digix.challenge.holanda.ms.popular.home.infrastructure.configuration;

import com.digix.challenge.holanda.ms.popular.home.application.responses.ValidationExceptionResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedExceptionHandling {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationExceptionResponse> handle(ValidationException exception, WebRequest webRequest) {
        ValidationExceptionResponse response = new ValidationExceptionResponse(
                exception.getMessage(),
                exception.getCode()
        );

        ResponseEntity<ValidationExceptionResponse> entity = new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

        return entity;
    }
}
