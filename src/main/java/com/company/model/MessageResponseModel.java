package com.company.model;

import java.io.Serializable;
import java.sql.Date;

public class MessageResponseModel implements Serializable {

	private int id;
	private String subject;
	private String message;
	private Date date;
	private boolean isSentMessage;
	private boolean isReceivedMessage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
