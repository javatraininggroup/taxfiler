package com.company.model;

import java.util.List;

public class BasicInformation {

	private String filingStatus;
	private Name name;
	private String ssn;
	private String dateOfBirth;
	private String occupation;
	private String dateOfMarriage;
	private String firstDateOfEntyInUS;
	private String typeOfVisa;
	private String citizenship;

	private ContactDetails contactDetails;
	private List<ResidencyDetailsforStates> residencyDetailsforStates;

	public String isFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDateOfMarriage() {
		return dateOfMarriage;
	}

	public void setDateOfMarriage(String dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}

	public String getFirstDateOfEntyInUS() {
		return firstDateOfEntyInUS;
	}

	public void setFirstDateOfEntyInUS(String firstDateOfEntyInUS) {
		this.firstDateOfEntyInUS = firstDateOfEntyInUS;
	}

	public String getTypeOfVisa() {
		return typeOfVisa;
	}

	public void setTypeOfVisa(String typeOfVisa) {
		this.typeOfVisa = typeOfVisa;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<ResidencyDetailsforStates> getResidencyDetailsforStates() {
		return residencyDetailsforStates;
	}

	public void setResidencyDetailsforStates(List<ResidencyDetailsforStates> residencyDetailsforStates) {
		this.residencyDetailsforStates = residencyDetailsforStates;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

}
