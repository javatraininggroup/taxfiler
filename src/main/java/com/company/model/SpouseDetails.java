package com.company.model;

import java.util.Date;

public class SpouseDetails {

	private String name;
	private String DateOfBirth;
	private String ssn_itin;
	private String checkIfITINToBeApplied;
	private String checkIfITINToBeRenewed;
	private String ITIN_Renewed;
	private String entryDateInto_US;
	private String occupation;

	private int TaxYear;
	private String stateResided;
	private Date startDate;
	private Date endDate;
	private boolean didYourSpouseisWorkedin2019;

	public int getTaxYear() {
		return TaxYear;
	}

	public void setTaxYear(int taxYear) {
		TaxYear = taxYear;
	}

	public String getStateResided() {
		return stateResided;
	}

	public void setStateResided(String stateResided) {
		this.stateResided = stateResided;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getSsn_itin() {
		return ssn_itin;
	}

	public void setSsn_itin(String ssn_itin) {
		this.ssn_itin = ssn_itin;
	}

	public String getCheckIfITINToBeApplied() {
		return checkIfITINToBeApplied;
	}

	public void setCheckIfITINToBeApplied(String checkIfITINToBeApplied) {
		this.checkIfITINToBeApplied = checkIfITINToBeApplied;
	}

	public String getCheckIfITINToBeRenewed() {
		return checkIfITINToBeRenewed;
	}

	public void setCheckIfITINToBeRenewed(String checkIfITINToBeRenewed) {
		this.checkIfITINToBeRenewed = checkIfITINToBeRenewed;
	}

	public String getITIN_Renewed() {
		return ITIN_Renewed;
	}

	public void setITIN_Renewed(String iTIN_Renewed) {
		ITIN_Renewed = iTIN_Renewed;
	}

	public String getEntryDateInto_US() {
		return entryDateInto_US;
	}

	public void setEntryDateInto_US(String entryDateInto_US) {
		this.entryDateInto_US = entryDateInto_US;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public boolean isDidYourSpouseisWorkedin2019() {
		return didYourSpouseisWorkedin2019;
	}

	public void setDidYourSpouseisWorkedin2019(boolean didYourSpouseisWorkedin2019) {
		this.didYourSpouseisWorkedin2019 = didYourSpouseisWorkedin2019;
	}

}
