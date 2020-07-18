package com.company.model;

import java.util.Date;

public class BasicInformation {

	private boolean martialStatus;
	private String nameAsPerSSN;
	private Long ssn;
	private Date dateOfBirth;
	private String Occupation;
	private Date dateOfMarriage;
	private Date firstDateOfEntyInUS;
	private String typeOfVisa;

	public boolean isMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(boolean martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getNameAsPerSSN() {
		return nameAsPerSSN;
	}

	public void setNameAsPerSSN(String nameAsPerSSN) {
		this.nameAsPerSSN = nameAsPerSSN;
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
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
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

}
