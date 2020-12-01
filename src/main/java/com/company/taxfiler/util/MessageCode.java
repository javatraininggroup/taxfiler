package com.company.taxfiler.util;

public enum MessageCode {
	
	NAME_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+1,"taxfiler.name_null_or_empty"),
	USER_NOT_REGISTERED(Constants.TAXFILER_MESSAGE_BASE+2, "taxfiler.user_not_registered"),
	EMAIL_OR_PASSWORD_NOT_MATCHED(Constants.TAXFILER_MESSAGE_BASE+3, "taxfiler.email_or_password_not_matched"),
	EMAIL_IS_ALREADY_REGISTERED(Constants.TAXFILER_MESSAGE_BASE+4, "taxfiler.email_is_already_registered"),
	EMAIL_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+5,"taxfiler.email_null_or_empty"),
	AN_ERROR_HAS_OCCURED(Constants.TAXFILER_MESSAGE_BASE+6, "taxfiler.an_error_has_occured"),
	DETAILS_NOT_AVAILABLE(Constants.TAXFILER_MESSAGE_BASE+7, "taxfiler.details_not_available"),
	DEPENDENT_INFO_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+8, "taxfiler.dependent_info_null_or_empty");
	
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
