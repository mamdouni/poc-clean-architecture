package com.architecture.java.infra.primary.rest.config.errorhandler;

import com.architecture.java.business.domain.exceptions.BadRequestException;
import com.architecture.java.business.domain.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;

@RestControllerAdvice
public class ApiErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ErrorResponse handleNotFoundResource(@NonNull ResourceNotFoundException ex) {

        return buildError(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException ex) {

        return buildError(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

    private ErrorResponse buildError(String message, Serializable details) {
        return new ErrorResponse()
                .setError(message)
                .setErrorDescription(details);
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class ErrorResponse {

        private String error;
        private Serializable errorDescription;
        private Object errorDetails;
    }
}
