package com.architecture.java.business.domain.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionsMessagesEnum {

	NOT_FOUND_USER("The User [id = %s] you are looking for is not found"),
	NOT_FOUND_TASK("The Task [id = %s] you are looking for is not found");

	private final String message;

	ExceptionsMessagesEnum(String message){
		this.message = message;
	}
}
