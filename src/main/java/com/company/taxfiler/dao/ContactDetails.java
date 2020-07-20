package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contact_details")
public class ContactDetails {
	
	//@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;
	private long id;
	//@Column(name="current_address")
	private String currentAddress;
	private String contact;
	//@Column(name="time_zone")
	private boolean timeZone;
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
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isTimeZone() {
		return timeZone;
	}
	public void setTimeZone(boolean timeZone) {
		this.timeZone = timeZone;
	}
	

}
