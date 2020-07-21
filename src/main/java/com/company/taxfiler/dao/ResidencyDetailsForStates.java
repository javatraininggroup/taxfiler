package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "residency_details_for_states")
public class ResidencyDetailsForStates {

	private long id;
	@Column(name = "tax_year")
	private long taxYear;
	@Column(name = "states_resided")
	private String statesResided;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(long taxYear) {

		this.taxYear = taxYear;
	}

	public String getStatesResided() {

		return statesResided;
	}

	public void setStatesResided(String statesResided) {
		this.statesResided = statesResided;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}