package com.company.taxfiler.model;

import java.io.Serializable;

public class RegistraionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	private String name;
	private String phone;
	private String alternatePhone;
	private String sourceOfKnowingSite;
	private String preferredTimezone;
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPreferredTimezone() {
		return preferredTimezone;
	}

	public void setPreferredTimezone(String preferredTimezone) {
		this.preferredTimezone = preferredTimezone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
