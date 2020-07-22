package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.company.model.DependentInformation;
import com.company.model.SpouseDetails;


@Entity
@Table(name = "tax_filed_year")
public class TaxFiledYearEntity {

	private long id;
	private long year;
	@Column(name = "user_id")
	private long userId;
	
	private BasicInfoEntity basicInfo;
	private ContactDetailsEntity contactDetails;
	private ResidencyDetailsForStatesEntity residencyDetailsforStates;
	private SpouseDetails spouseDetails;
	private DependentInformation dependentInformation; 
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getYear() {

		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	

}
