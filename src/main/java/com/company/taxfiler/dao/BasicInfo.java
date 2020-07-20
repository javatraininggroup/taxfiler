package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "basic_info")
public class BasicInfo {
	
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
