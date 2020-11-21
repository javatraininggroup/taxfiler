package com.company.taxfiler.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	@Column(name = "alternate_phone")
	private String alternatePhone;
	@Column(name = "source_of_knowing_site")
	private String sourceOfKnowingSite;
	@Column(name = "timezone")
	private String timezone;
	/*
	 * @Column(name = "verification_code") private String verificationCode;
	 */
	@Column(name = "confirm_details")
	private boolean confirmDetails;
	private String role;

	@OneToMany(/* orphanRemoval = true, */ mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = TaxFiledYearEntity.class)
	// @JsonBackReference
	@JsonManagedReference
	private Set<TaxFiledYearEntity> taxFiledYearList;

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

	/*
	 * public String getVerificationCode() { return verificationCode; }
	 * 
	 * public void setVerificationCode(String verificationCode) {
	 * this.verificationCode = verificationCode; }
	 */

	public boolean isConfirmDetails() {
		return confirmDetails;
	}

	public void setConfirmDetails(boolean confirmDetails) {
		this.confirmDetails = confirmDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Set<TaxFiledYearEntity> getTaxFiledYearList() {
		if (null == taxFiledYearList) {
			taxFiledYearList = new HashSet<>();
		}
		return taxFiledYearList;
	}

	public void setTaxFiledYearList(Set<TaxFiledYearEntity> taxFiledYearList) {
		this.taxFiledYearList = taxFiledYearList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
