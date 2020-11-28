package com.company.model;

import java.util.List;

public class DependentInformation {

	private Name name;
	private String dateOfBirth;
	private String ssnOrItin;
	private boolean checkIfITINToBeApplied;
	private boolean checkIfITINToBeRenewed;
	private boolean ITINRenewed;
	private String relationship;
	private String visaStatus;
	private boolean isLivingMoreThan6Months;
	private boolean ifProvidedMoreThan50PERSupportDuringTheYearXX;
	private List<ResidencyDetailsforStates> residencyDetailsforStates;
	private boolean ifYouAndYourSpouseAreWorking;
	private DayCareModel dayCareDetails;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public List<ResidencyDetailsforStates> getResidencyDetailsforStates() {
		return residencyDetailsforStates;
	}

	public void setResidencyDetailsforStates(List<ResidencyDetailsforStates> residencyDetailsforStates) {
		this.residencyDetailsforStates = residencyDetailsforStates;
	}

	public String getSsnOrItin() {
		return ssnOrItin;
	}

	public void setSsnOrItin(String ssnOrItin) {
		this.ssnOrItin = ssnOrItin;
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

	public boolean isLivingMoreThan6Months() {
		return isLivingMoreThan6Months;
	}

	public void setLivingMoreThan6Months(boolean isLivingMoreThan6Months) {
		this.isLivingMoreThan6Months = isLivingMoreThan6Months;
	}

	public boolean isIfProvidedMoreThan50PERSupportDuringTheYearXX() {
		return ifProvidedMoreThan50PERSupportDuringTheYearXX;
	}

	public void setIfProvidedMoreThan50PERSupportDuringTheYearXX(
			boolean ifProvidedMoreThan50PERSupportDuringTheYearXX) {
		this.ifProvidedMoreThan50PERSupportDuringTheYearXX = ifProvidedMoreThan50PERSupportDuringTheYearXX;
	}

	public boolean isIfYouAndYourSpouseAreWorking() {
		return ifYouAndYourSpouseAreWorking;
	}

	public void setIfYouAndYourSpouseAreWorking(boolean ifYouAndYourSpouseAreWorking) {
		this.ifYouAndYourSpouseAreWorking = ifYouAndYourSpouseAreWorking;
	}

	public DayCareModel getDayCareDetails() {
		return dayCareDetails;
	}

	public void setDayCareDetails(DayCareModel dayCareDetails) {
		this.dayCareDetails = dayCareDetails;
	}

}
