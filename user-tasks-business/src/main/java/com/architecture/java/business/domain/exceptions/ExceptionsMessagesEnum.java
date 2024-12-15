package com.architecture.java.business.domain.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionsMessagesEnum {

	NOT_FOUND_USER("The User [id = %s] you are looking for is not found"),
	NOT_FOUND_TASK("The Task [id = %s] you are looking for is not found"),
	EMPTY_REQUEST("The request must not be empty"),
	EMPTY_USERNAME("The username must not be empty"),
	NOT_VAlID_EMAIL("The email must be valid"),
	NOT_VAlID_TASK("The task must be valid"),;

	private final String message;

	ExceptionsMessagesEnum(String message){
		this.message = message;
	}
}
