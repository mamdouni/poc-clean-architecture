package com.architecture.java.cleanarchi.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	protected final ExceptionsMessagesEnum messagesEnum;

	public ResourceNotFoundException(ExceptionsMessagesEnum messagesEnum) {
		super(messagesEnum.getMessage());
		this.messagesEnum = messagesEnum;
	}
}
