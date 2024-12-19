package com.architecture.java.layered.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionsMessagesEnum {

	NOT_FOUND_USER("The User you are looking for is not found"),
	NOT_FOUND_TASK("The Task you are looking for is not found");

	private final String message;

	ExceptionsMessagesEnum(String message){
		this.message = message;
	}
}
