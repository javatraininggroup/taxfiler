package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity	
@Table(name="other_informtion")
public class OtherInformation {
	
	private long id;
	
	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTaxFiledYearId() {
		return taxFiledYearId;
	}

	public void setTaxFiledYearId(long taxFiledYearId) {
		this.taxFiledYearId = taxFiledYearId;
	}

	@Column(name="please_update_if_any_other_information")
	
	private String pleaseUpdateIfAnyOtherInformation;

	public String getPleaseUpdateIfAnyOtherInformation() {
		return pleaseUpdateIfAnyOtherInformation;
	}

	public void setPleaseUpdateIfAnyOtherInformation(String pleaseUpdateIfAnyOtherInformation) {
		this.pleaseUpdateIfAnyOtherInformation = pleaseUpdateIfAnyOtherInformation;
	}
}
