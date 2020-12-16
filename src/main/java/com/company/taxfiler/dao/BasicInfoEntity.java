package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "basic_info")
public class BasicInfoEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	// @JsonManagedReference
	@JsonBackReference
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "filing_status")
	private String filingStatus;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;
	private String ssn;
	private Date dob;
	private String occupation;
	@Column(name = "date_of_marriage")
	private Date dateOfMarriage;
	@Column(name = "first_date_of_entry_in_US")
	private Date firstDateOfEntryInUS;
	@Column(name = "type_of_visa")
	private String typeOfVisa;
	private String citizenship;
	@Column(name = "did_you_work_more_than_one_employer_in_XX")
	private boolean didYouWorkMoreThanOneEmployerInXX;
	@Column(name = "more_than_one_employer_work_status_comments")
	private String moreThanOneEmployerWorkStatusComments;
	//private String timezone;

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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
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

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public boolean isDidYouWorkMoreThanOneEmployerInXX() {
		return didYouWorkMoreThanOneEmployerInXX;
	}

	public void setDidYouWorkMoreThanOneEmployerInXX(boolean didYouWorkMoreThanOneEmployerInXX) {
		this.didYouWorkMoreThanOneEmployerInXX = didYouWorkMoreThanOneEmployerInXX;
	}

	public String getMoreThanOneEmployerWorkStatusComments() {
		return moreThanOneEmployerWorkStatusComments;
	}

	public void setMoreThanOneEmployerWorkStatusComments(String moreThanOneEmployerWorkStatusComments) {
		this.moreThanOneEmployerWorkStatusComments = moreThanOneEmployerWorkStatusComments;
	}

/*	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}*/

}
