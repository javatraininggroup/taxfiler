package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "residency_details_for_states")
public class ResidencyDetailsForStatesEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	@JsonManagedReference
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tax_year")
	private long taxYear;
	@Column(name = "states_resided")
	private String statesResided;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "is_this_spouse")
	private boolean areDetailsForSpouse;
	@Column(name = "type_of_residency_details")
	private String typeOfResidencyDetails;

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public boolean isAreDetailsForSpouse() {
		return areDetailsForSpouse;
	}

	public void setAreDetailsForSpouse(boolean areDetailsForSpouse) {
		this.areDetailsForSpouse = areDetailsForSpouse;
	}

	public String getTypeOfResidencyDetails() {
		return typeOfResidencyDetails;
	}

	public void setTypeOfResidencyDetails(String typeOfResidencyDetails) {
		this.typeOfResidencyDetails = typeOfResidencyDetails;
	}

}
