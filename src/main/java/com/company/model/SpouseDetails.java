package com.company.model;

import java.util.Set;

public class SpouseDetails {
	private long id;

	private Name name;
	private String DateOfBirth;
	private String ssnOrItin;
	private boolean checkIfITINToBeApplied;
	private boolean checkIfITINToBeRenewed;
	private boolean ITINRenewed;
	private String entryDateIntoUS;
	private String occupation;
	private Set<ResidencyDetailsforStates> addressOfLivingInTaxYear;
	private boolean didYourSpouseisWorkedinXX;
	private boolean isLivingMoreThan6Months;
	private String spouseWorkStatusComments;
	private boolean didYouWorkMoreThanOneEmployerInXX;
	private String moreThanOneEmployerWorkStatusComments;

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

	public Set<ResidencyDetailsforStates> getAddressOfLivingInTaxYear() {
		return addressOfLivingInTaxYear;
	}

	public void setAddressOfLivingInTaxYear(Set<ResidencyDetailsforStates> addressOfLivingInTaxYear) {
		this.addressOfLivingInTaxYear = addressOfLivingInTaxYear;
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

}
