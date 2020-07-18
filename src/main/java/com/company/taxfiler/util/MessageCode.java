package com.company.taxfiler.util;

public enum MessageCode {
	
	NAME_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+1,"taxfiler.name_null_or_empty");
	
	private int key;
	private String value;
	
	MessageCode(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	

}
