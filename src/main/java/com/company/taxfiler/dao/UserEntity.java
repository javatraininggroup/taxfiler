package com.company.taxfiler.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
	private long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String alternatePhone;
	private String sourceOfKnowingSite;
	private String timeZone;
	private String verificationCode;
	private boolean confirmDetails;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAlternatePhone() {
		return alternatePhone;
	}
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}
	public String getSourceOfKnowingSite() {
		return sourceOfKnowingSite;
	}
	public void setSourceOfKnowingSite(String sourceOfKnowingSite) {
		this.sourceOfKnowingSite = sourceOfKnowingSite;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public boolean isConfirmDetails() {
		return confirmDetails;
	}
	public void setConfirmDetails(boolean confirmDetails) {
		this.confirmDetails = confirmDetails;
	}
	
	
	
	

}
