package com.company.taxfiler.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	private long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	@Column(name = "alternate_phone")
	private String alternatePhone;
	@Column(name = "source_of_knowing_site")
	private String sourceOfKnowingSite;
	@Column(name = "time_zone")
	private String timezone;
	@Column(name = "verification_code")
	private String verificationCode;
	@Column(name = "confirm_details")
	private boolean confirmDetails;

	@OneToMany
	private List<TaxFiledYearEntity> taxFiledYearList;

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
