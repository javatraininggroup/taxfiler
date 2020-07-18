package com.company.model;

import java.util.List;

public class ContactDetails {

	private String address;
	private String city;
	private String aptNo;
	private String state;
	private int zip;
	private String country;
	private Long mobilePhone;
	private Long alternateNumber;
	private Long indiaNumber;
	private String email;
	private String timeZone;
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

	public Long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
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

}
