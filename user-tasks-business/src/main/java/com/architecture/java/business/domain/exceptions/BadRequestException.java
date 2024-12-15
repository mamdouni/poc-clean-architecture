package com.architecture.java.business.domain.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(ExceptionsMessagesEnum messagesEnum) {
        super(messagesEnum.getMessage());
    }

}
