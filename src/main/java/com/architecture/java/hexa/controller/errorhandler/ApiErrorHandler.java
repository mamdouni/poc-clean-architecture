package com.architecture.java.hexa.controller.errorhandler;

import com.architecture.java.hexa.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.Serializable;

@RestControllerAdvice
public class ApiErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class, NoResourceFoundException.class})
    public ErrorResponse handleNotFoundResource(@NonNull ResourceNotFoundException ex) {

        return buildError(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessagesEnum().getMessage());
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
