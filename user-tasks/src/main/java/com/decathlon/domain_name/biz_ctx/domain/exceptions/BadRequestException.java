package com.decathlon.domain_name.biz_ctx.domain.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(ExceptionsMessagesEnum messagesEnum) {
        super(messagesEnum.getMessage());
    }

}
