package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "basic_info")
public class BasicInfo {

	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;

	private long id;
	@Column(name = "martial_status")
	private boolean martialStatus;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;
	private int ssn;
	private Date dob;
	private String occupation;
	@Column(name = "date_of_marriage")
	private Date dateOfMarriage;
	@Column(name = "first_date_of_entry_in_US")
	private Date firstDateOfEntryInUS;
	@Column(name = "type_of_visa")
	private String typeOfVisa;

	public long getTaxFiledYearId() {
		return taxFiledYearId;
	}

	public void setTaxFiledYearId(long taxFiledYearId) {
		this.taxFiledYearId = taxFiledYearId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(boolean martialStatus) {
		this.martialStatus = martialStatus;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getTypeOfVisa() {
		return typeOfVisa;
	}

	public void setTypeOfVisa(String typeOfVisa) {
		this.typeOfVisa = typeOfVisa;
	}

	public Date getDateOfMarriage() {
		return dateOfMarriage;
	}

	public void setDateOfMarriage(Date dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}

	public Date getFirstDateOfEntryInUS() {
		return firstDateOfEntryInUS;
	}

	public void setFirstDateOfEntryInUS(Date firstDateOfEntryInUS) {
		this.firstDateOfEntryInUS = firstDateOfEntryInUS;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
