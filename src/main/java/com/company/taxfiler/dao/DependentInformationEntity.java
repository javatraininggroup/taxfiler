package com.company.taxfiler.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.company.model.ResidencyDetailsforStates;

@Entity
public class DependentInformationEntity {

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "dob")
	private String dateOfBirth;
	@Column(name = "ssn_itin")
	private String ssnitin;
	@Column(name = "check_if_ITIN_to_be_applied")
	private boolean checkIfITINToBeApplied;
	@Column(name = "check_if_ITIN_to_be_Renewed")
	private boolean checkIfITINToBeRenewed;
	@Column(name = "ITIN_Renewed")
	private boolean ITINRenewed;
	private String relationship;
	@Column(name = "visa_status")
	private String visaStatus;
	@Column(name = "lived_for_more_than_06_months")
	private boolean livedForMoreThan06Months;
	@Column(name = "provided_more_than_50PE_support")
	private boolean providedMoreThan50PESupport;
	@Column(name = "is_you_and_spouse_working")
	private boolean isYouAndSpouseWorking;

	/**
	 * hibernate mapping
	 */
	@Column(name = "")
	private List<ResidencyDetailsforStates> residencyDetailsforStates;

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

	public String getSsnitin() {
		return ssnitin;
	}

	public void setSsnitin(String ssnitin) {
		this.ssnitin = ssnitin;
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

	public boolean isLivedForMoreThan06Months() {
		return livedForMoreThan06Months;
	}

	public void setLivedForMoreThan06Months(boolean livedForMoreThan06Months) {
		this.livedForMoreThan06Months = livedForMoreThan06Months;
	}

	public boolean isProvidedMoreThan50PESupport() {
		return providedMoreThan50PESupport;
	}

	public void setProvidedMoreThan50PESupport(boolean providedMoreThan50PESupport) {
		this.providedMoreThan50PESupport = providedMoreThan50PESupport;
	}

	public boolean isYouAndSpouseWorking() {
		return isYouAndSpouseWorking;
	}

	public void setYouAndSpouseWorking(boolean isYouAndSpouseWorking) {
		this.isYouAndSpouseWorking = isYouAndSpouseWorking;
	}

}
