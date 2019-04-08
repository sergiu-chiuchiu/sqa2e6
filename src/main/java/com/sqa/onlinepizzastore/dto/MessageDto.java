package com.sqa.onlinepizzastore.dto;

import org.springframework.stereotype.Component;

@Component
public class MessageDto {
	private String messageType;
	private String message;

	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MessageDto(String messageType, String message) {
		super();
		this.messageType = messageType;
		this.message = message;
	}
	
	public MessageDto() {
		super();
	}

	
	
}
