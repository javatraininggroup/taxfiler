package com.company.model;

import java.util.Date;

public class ContactDetails {

	private String citizenship;
	private String currentAddress;
	private String city;
	private String aptNo;
	private String state;
	private int zip;
	private String country;
	private Long contact;
	private Long alternateNumber;
	private Long indiaNumber;
	private String email;
	private String timeZone;

	private int TaxYear;
	private String stateResided;
	private Date startDate;
	private Date endDate;

	public int getTaxYear() {
		return TaxYear;
	}

	public void setTaxYear(int taxYear) {
		TaxYear = taxYear;
	}

	public String getStateResided() {
		return stateResided;
	}

	public void setStateResided(String stateResided) {
		this.stateResided = stateResided;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAptNo() {
		return aptNo;
	}

	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public Long getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(Long alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public Long getIndiaNumber() {
		return indiaNumber;
	}

	public void setIndiaNumber(Long indiaNumber) {
		this.indiaNumber = indiaNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
