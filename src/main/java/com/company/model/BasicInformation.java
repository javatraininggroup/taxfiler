package com.company.model;

import java.util.Date;

public class BasicInformation {

	private String martialStatus;
	private Name name;
	private Long ssn;
	private Date dateOfBirth;
	private String occupation;
	private Date dateOfMarriage;
	private Date firstDateOfEntyInUS;
	private String typeOfVisa;
	private String citizenship;

	public String isMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Date getDateOfMarriage() {
		return dateOfMarriage;
	}

	public void setDateOfMarriage(Date dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}

	public Date getFirstDateOfEntyInUS() {
		return firstDateOfEntyInUS;
	}

	public void setFirstDateOfEntyInUS(Date firstDateOfEntyInUS) {
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

	public String getMartialStatus() {
		return martialStatus;
	}

}
