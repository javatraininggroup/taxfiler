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
@Table(name = "spouse_details")
public class SpouseDetailsEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	// @JsonManagedReference
	@JsonBackReference
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "dob")
	private Date DateOfBirth;
	@Column(name = "ssn_or_itin")
	private String ssnOrItin;
	@Column(name = "check_if_ITIN_to_be_applied")
	private boolean checkIfITINToBeApplied;
	@Column(name = "check_if_ITIN_to_be_renewed")
	private boolean checkIfITINToBeRenewed;
	@Column(name = "itin_renewed")
	private boolean ITINRenewed;
	@Column(name = "entry_date_into_US")
	private Date entryDateIntoUS;
	private String occupation;

	// private List<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList;

	@Column(name = "did_your_spouse_is_worked_in_XX")
	private boolean didYourSpouseisWorkedinXX;
	@Column(name = "is_living_more_than_6months")
	private boolean isLivingMoreThan6Months;
	@Column(name = "spouse_work_status_comments")
	private String spouseWorkStatusComments;
	@Column(name = "did_you_work_more_than_one_employer_in_XX")
	private boolean didYouWorkMoreThanOneEmployerInXX;
	@Column(name = "more_than_one_employer_work_status_comments")
	private String moreThanOneEmployerWorkStatusComments;
	//@Column(name = "type_of_visa")
	//private String typeOfVisa;

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

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getSsnOrItin() {
		return ssnOrItin;
	}

	public void setSsnOrItin(String ssnOrItin) {
		this.ssnOrItin = ssnOrItin;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/*
	 * public List<ResidencyDetailsForStatesEntity>
	 * getResidencyDetailsforStatesList() { return residencyDetailsforStatesList; }
	 * 
	 * public void
	 * setResidencyDetailsforStatesList(List<ResidencyDetailsForStatesEntity>
	 * residencyDetailsforStatesList) { this.residencyDetailsforStatesList =
	 * residencyDetailsforStatesList; }
	 */

	public void setEntryDateIntoUS(Date entryDateIntoUS) {
		this.entryDateIntoUS = entryDateIntoUS;
	}

	public boolean isCheckIfITINToBeApplied() {
		return checkIfITINToBeApplied;
	}

	public void setCheckIfITINToBeApplied(boolean checkIfITINToBeApplied) {
		this.checkIfITINToBeApplied = checkIfITINToBeApplied;
	}

	public boolean isCheckIfITINToBeRenewed() {
		return checkIfITINToBeRenewed;
	}

	public void setCheckIfITINToBeRenewed(boolean checkIfITINToBeRenewed) {
		this.checkIfITINToBeRenewed = checkIfITINToBeRenewed;
	}

	public boolean isITINRenewed() {
		return ITINRenewed;
	}

	public void setITINRenewed(boolean iTINRenewed) {
		ITINRenewed = iTINRenewed;
	}

	public boolean isITIN_Renewed() {
		return ITINRenewed;
	}

	public void setITIN_Renewed(boolean iTIN_Renewed) {
		ITINRenewed = iTIN_Renewed;
	}

	public boolean isLivingMoreThan6Months() {
		return isLivingMoreThan6Months;
	}

	public void setLivingMoreThan6Months(boolean isLivingMoreThan6Months) {
		this.isLivingMoreThan6Months = isLivingMoreThan6Months;
	}

	public boolean isDidYourSpouseisWorkedinXX() {
		return didYourSpouseisWorkedinXX;
	}

	public void setDidYourSpouseisWorkedinXX(boolean didYourSpouseisWorkedinXX) {
		this.didYourSpouseisWorkedinXX = didYourSpouseisWorkedinXX;
	}

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public Date getEntryDateIntoUS() {
		return entryDateIntoUS;
	}

	public String getSpouseWorkStatusComments() {
		return spouseWorkStatusComments;
	}

	public void setSpouseWorkStatusComments(String spouseWorkStatusComments) {
		this.spouseWorkStatusComments = spouseWorkStatusComments;
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

/*	public String getTypeOfVisa() {
		return typeOfVisa;
	}

	public void setTypeOfVisa(String typeOfVisa) {
		this.typeOfVisa = typeOfVisa;
	}*/

}
