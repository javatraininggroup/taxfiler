package com.company.model;

import java.io.Serializable;
import java.util.List;

import com.company.taxfiler.dao.MessagesEntity;
import com.company.taxfiler.dao.UploadFilesEntity;

public class EmployeePortalInformationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;

	private List<MessagesEntity> messagesList;

	private List<UploadFilesEntity> uploadedFilesList;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<MessagesEntity> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<MessagesEntity> messagesList) {
		this.messagesList = messagesList;
	}

	public List<UploadFilesEntity> getUploadedFilesList() {
		return uploadedFilesList;
	}

	public void setUploadedFilesList(List<UploadFilesEntity> uploadedFilesList) {
		this.uploadedFilesList = uploadedFilesList;
	}

}