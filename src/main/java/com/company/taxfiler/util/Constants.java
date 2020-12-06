package com.company.taxfiler.util;

public class Constants {
	public static final int TAXFILER_MESSAGE_BASE = 10000;
	public static final String SUCCESS = "success";
	public static final String USER_ID = "user_id";
	public static final String TAX_YEAR = "tax_year";
	public static final String MAIN_STATUS = "mainStatus";
	public static final String SUB_STATUS = "subStatus";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_TYPE = "fileType";
	public static final String COMMENT = "comment";
	public static final String FILE = "file";
	public static final String FILE_ID = "fileId";

	public static final String API = "/api";
	public static final String GET_SENT_MESSAGES_ENDPOINT = "/updateuser/{user_id}/{tax_year}/sentMessages";
	public static final String GET_CLIENT_DETAILS_ENDPOINT = "/clientDetails/{user_id}/{tax_year}/allDetails";
	public static final String GET_USERS_MESSAGES_AND_DOCS_ENDPOINT = "/users/{user_id}/taxYear/{tax_year}/mainStatus/{mainStatus}/subStatus/{subStatus}/customersInfo";
	public static final String POST_LOGIN_USER_ENDPOINT = "/login";
	public static final String POST_REGISTER_USER_ENDPOINT = "/register";
	public static final String POST_EDIT_PROFILE_ENDPOINT = "/updateuser/{user_id}/settings/editProfile";
	public static final String POST_CHANGE_PASSWORD_ENDPOINT = "/updateuser/{user_id}/settings/changePassword";
	public static final String PUT_UPDATE_USER_BASIC_INFO_ENDPOINT = "/updateuser/{user_id}/{tax_year}/basicInfo";
	public static final String GET_USER_BASIC_INFO_ENDPOINT = "/{user_id}/{tax_year}/basicInfo";
	public static final String PUT_UPDATE_USER_DEPENDENT_INFO_ENDPOINT = "/updateuser/{user_id}/{tax_year}/dependentInfo";
	public static final String GET_USER_DEPENDENT_INFO_ENDPOINT = "/updateuser/{user_id}/{tax_year}/dependentInfo";
	public static final String PUT_UPDATE_USER_BANK_INFO_ENDPOINT = "/updateuser/{user_id}/{tax_year}/bankInfo";
	public static final String GET_USER_BANK_INFO_ENDPOINT = "/updateuser/{user_id}/{tax_year}/bankInfo";
	public static final String PUT_OTHER_INCOME_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/otherIncomeInfo";
	public static final String GET_OTHER_INCOME_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/otherIncomeInfo";
	public static final String PUT_ADDITIONAL_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/additionalInfo";
	public static final String GET_ADDITIONAL_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/additionalInfo";
	public static final String PUT_OTHER_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/otherInfo";
	public static final String GET_OTHER_INFORMATION_ENDPOINT = "/updateuser/{user_id}/{tax_year}/otherInfo";
	public static final String PUT_FBAR_ENDPOINT = "/updateuser/{user_id}/{tax_year}/fbar";
	public static final String GET_FBAR_ENDPOINT = "/updateuser/{user_id}/{tax_year}/fbar";
	public static final String POST_MESSAGE_ENDPOINT = "/updateuser/{user_id}/{tax_year}/message";
	public static final String GET_ALL_MESSAGES_ENDPOINT = "/updateuser/{user_id}/{tax_year}/message";
	public static final String GET_RECEIVED_MESSAGES_ENDPOINT = "/updateuser/{user_id}/{tax_year}/receivedMessages";
	public static final String POST_UPLOAD_DOCS_ENDPOINT = "/upload/{user_id}/{tax_year}";
	public static final String GET_DOWNLOAD_FILE_ENDPOINT = "/download/{user_id}/{tax_year}/{fileId}";
	public static final String GET_ALL_UPLOAD_DOCS_ENDPOINT = "/docs/{user_id}/{tax_year}";
	public static final String UPDATE_USER_INFO_STATUS_BY_EMPLOYEE_ENDPOINT = "/employee/updateuser/{user_id}/{tax_year}/status";
	public static final String RENTAL_INCOME_DETAIL_ENDPOINT = "/updateuser/{user_id}/{tax_year}/rentalIncome";

}
