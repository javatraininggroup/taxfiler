package com.company.taxfiler.util;

public enum MessageCode {
	
	NAME_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+1,"taxfiler.name_null_or_empty"),
	USER_NOT_REGISTERED(Constants.TAXFILER_MESSAGE_BASE+2, "taxfiler.user_not_registered"),
	EMAIL_OR_PASSWORD_NOT_MATCHED(Constants.TAXFILER_MESSAGE_BASE+3, "taxfiler.email_or_password_not_matched"),
	EMAIL_IS_ALREADY_REGISTERED(Constants.TAXFILER_MESSAGE_BASE+4, "taxfiler.email_is_already_registered"),
	EMAIL_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+5,"taxfiler.email_null_or_empty"),
	AN_ERROR_HAS_OCCURED(Constants.TAXFILER_MESSAGE_BASE+6, "taxfiler.an_error_has_occured"),
	BASIC_INFO_DETAILS_NOT_AVAILABLE(Constants.TAXFILER_MESSAGE_BASE+7, "taxfiler.basic_info_details_not_available"),
	USER_DEPENDENT_INFO_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+8, "taxfiler.user dependent_info_null_or_empty"),
	NEW_PASSWORD_NOT_MATCHED_WITH_CONFIRM_PASSWORD(Constants.TAXFILER_MESSAGE_BASE+9, "taxfiler.new_password_not_matched_with_confirm_password"),
	CURRENT_PASSWORD_IS_INVALID_PLEASE_TRY_WITH_VALID_PASSWORD(Constants.TAXFILER_MESSAGE_BASE+10, "taxfiler.current_password_is_invalid_please_try_with_valid_password"),
	FAILED_TO_UPLOAD_BECAUSE_FILE_WAS_EMPTY(Constants.TAXFILER_MESSAGE_BASE+11, "taxfiler.failed_to_upload_because_file_was_empty"),
	FILE_NOT_AVAILABLE_TO_DOWNLOAD(Constants.TAXFILER_MESSAGE_BASE+12, "taxfiler.file_not_availble_to_download"),
	INVALID_SESSION_ID(Constants.TAXFILER_MESSAGE_BASE+13, 	"taxfiler.invalid_session_id"),
	PASSWORD_NULL_OR_EMPTY(Constants.TAXFILER_MESSAGE_BASE+14, "taxfiler.password_null_or_empty");
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
