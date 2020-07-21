package com.company.model;

import java.util.List;

import javax.persistence.Column;

public class SpouseDetails {
    private long id;
	
	private Name name;
	@Column(name="date_of_birth")
	private String DateOfBirth;
	@Column(name="ssn_or_Itin")
	private String ssnOrItin;
	@Column(name="check_if_ITIN_to_be_applied")
	private boolean checkIfITINToBeApplied;
	@Column(name="check_if_ITIN_to_be_renewed")
	private boolean checkIfITINToBeRenewed;
	@Column(name="ITIN_renewed")
	private boolean ITINRenewed;
	@Column(name="entry_date_into_US")
	private String entryDateIntoUS;
	private String occupation;
	private List<ResidencyDetailsforStates> residencyDetailsforStatesList;
	@Column(name="did_your_spouse_is_worked_in_XX")
	private boolean didYourSpouseisWorkedinXX;
	@Column(name="is_living_more_than_6months")
	private boolean isLivingMoreThan6Months;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
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

	public List<ResidencyDetailsforStates> getResidencyDetailsforStatesList() {
		return residencyDetailsforStatesList;
	}

	public void setResidencyDetailsforStatesList(List<ResidencyDetailsforStates> residencyDetailsforStatesList) {
		this.residencyDetailsforStatesList = residencyDetailsforStatesList;
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
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEntryDateIntoUS() {
		return entryDateIntoUS;
	}

	public void setEntryDateIntoUS(String entryDateIntoUS) {
		this.entryDateIntoUS = entryDateIntoUS;
	}

	public boolean isDidYourSpouseisWorkedinXX() {
		return didYourSpouseisWorkedinXX;
	}

	public void setDidYourSpouseisWorkedinXX(boolean didYourSpouseisWorkedinXX) {
		this.didYourSpouseisWorkedinXX = didYourSpouseisWorkedinXX;
	}

}
