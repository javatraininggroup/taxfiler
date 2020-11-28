package com.company.model;

import java.util.List;

public class ClientDetails {
	private String email;
	private String maillingState;
	private String altPhone;
	private String regPhone;
	private String maililingZip;
	private String status;
	private String comments;
	private List<String> allComments;
	private List<DownloadModel> uploadDocsList;
	private String name;
	private String fileId;
	private int id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaillingState() {
		return maillingState;
	}

	public void setMaillingState(String maillingState) {
		this.maillingState = maillingState;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getRegPhone() {
		return regPhone;
	}

	public void setRegPhone(String regPhone) {
		this.regPhone = regPhone;
	}

	public String getMaililingZip() {
		return maililingZip;
	}

	public void setMaililingZip(String maililingZip) {
		this.maililingZip = maililingZip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getAllComments() {
		return allComments;
	}

	public void setAllComments(List<String> allComments) {
		this.allComments = allComments;
	}

	public List<DownloadModel> getUploadDocsList() {
		return uploadDocsList;
	}

	public void setUploadDocsList(List<DownloadModel> uploadDocsList) {
		this.uploadDocsList = uploadDocsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
