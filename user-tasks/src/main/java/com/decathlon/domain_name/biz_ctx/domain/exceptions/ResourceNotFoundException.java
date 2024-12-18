package com.decathlon.domain_name.biz_ctx.domain.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	protected final ExceptionsMessagesEnum messagesEnum;

	public ResourceNotFoundException(ExceptionsMessagesEnum messageEnum, Object... args) {
		super(messageEnum.getMessage().formatted(args));
		this.messagesEnum = messageEnum;
	}

}
