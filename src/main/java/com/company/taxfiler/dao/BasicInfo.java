package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "basic_info")
public class BasicInfo {
	
	private long id;
	
	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;
	
	private boolean martialStatus;
	private String name;
	private int ssn;
	private Date dob;
	private String occupation;
	private Date dateOfMarriage;
	private Date firstDateOfEnrtyInUS;
	private String typeOfVisa;
	
	public Date getDateOfMarriage() {
		return dateOfMarriage;
	}
	public void setDateOfMarriage(Date dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}

}
