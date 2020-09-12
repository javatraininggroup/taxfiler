package com.company.taxfiler.model;

import java.io.Serializable;

public class MessageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String message;
	private boolean isSentMessage;
	private boolean isReceivedMessage;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSentMessage() {
		return isSentMessage;
	}

	public void setSentMessage(boolean isSentMessage) {
		this.isSentMessage = isSentMessage;
	}

	public boolean isReceivedMessage() {
		return isReceivedMessage;
	}

	public void setReceivedMessage(boolean isReceivedMessage) {
		this.isReceivedMessage = isReceivedMessage;
	}

}
