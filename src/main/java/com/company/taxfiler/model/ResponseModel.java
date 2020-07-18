package com.company.taxfiler.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String code;
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
