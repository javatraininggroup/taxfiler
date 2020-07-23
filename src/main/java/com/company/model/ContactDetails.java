package com.company.model;

import java.util.List;

public class ContactDetails {

	private String address;
	private String city;
	private String aptNo;
	private String state;
	private int zip;
	private String country;
	private String mobilePhone;
	private String alternateNumber;
	private String indiaNumber;
	private String email;
	private String timezone;
	private List<ResidencyDetailsforStates> residencyDetailsforStates;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ResidencyDetailsforStates> getResidencyDetailsforStates() {
		return residencyDetailsforStates;
	}

	public void setResidencyDetailsforStates(List<ResidencyDetailsforStates> residencyDetailsforStates) {
		this.residencyDetailsforStates = residencyDetailsforStates;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getIndiaNumber() {
		return indiaNumber;
	}

	public void setIndiaNumber(String indiaNumber) {
		this.indiaNumber = indiaNumber;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}
