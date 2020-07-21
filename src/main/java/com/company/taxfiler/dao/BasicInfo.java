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
	
	private String name;
	private int ssn;
	private Date dob;
	private String occupation;
	
	@Column(name="date_of_marriage")
	private Date dateOfMarriage;
	
	@Column(name="first_date_of_entry_in_US")
	private Date firstDateOfEnrtyInUS;
	
	@Column(name="type_of_visa")
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getFirstDateOfEnrtyInUS() {
		return firstDateOfEnrtyInUS;
	}
	public void setFirstDateOfEnrtyInUS(Date firstDateOfEnrtyInUS) {
		this.firstDateOfEnrtyInUS = firstDateOfEnrtyInUS;
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

}
