package com.company.model;

import java.util.List;

public class DependentInformation {

	private Name name;
	private String dateOfBirth;
	private String ssn_itin;
	private boolean check_if_ITIN_to_be_Applied;
	private boolean check_if_ITIN_to_be_Renewed;
	private boolean ITIN_Renewed;
	private String relationship;
	private String visaStatus;
	private boolean check_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019;
	private boolean check_if_you_provided_more_than_50PER_support_during_the_year_2019;

	private List<ResidencyDetailsforStates> residencyDetailsforStates;
	private boolean check_if_you_and_your_spouse_are_working;

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

	public String getSsn_itin() {
		return ssn_itin;
	}

	public void setSsn_itin(String ssn_itin) {
		this.ssn_itin = ssn_itin;
	}

	public boolean isCheck_if_ITIN_to_be_Applied() {
		return check_if_ITIN_to_be_Applied;
	}

	public void setCheck_if_ITIN_to_be_Applied(boolean check_if_ITIN_to_be_Applied) {
		this.check_if_ITIN_to_be_Applied = check_if_ITIN_to_be_Applied;
	}

	public boolean isCheck_if_ITIN_to_be_Renewed() {
		return check_if_ITIN_to_be_Renewed;
	}

	public void setCheck_if_ITIN_to_be_Renewed(boolean check_if_ITIN_to_be_Renewed) {
		this.check_if_ITIN_to_be_Renewed = check_if_ITIN_to_be_Renewed;
	}

	public boolean isITIN_Renewed() {
		return ITIN_Renewed;
	}

	public void setITIN_Renewed(boolean iTIN_Renewed) {
		ITIN_Renewed = iTIN_Renewed;
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

	public boolean isCheck_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019() {
		return check_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019;
	}

	public void setCheck_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019(
			boolean check_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019) {
		this.check_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019 = check_if_lived_for_more_than_06_months_with_you_in_US_during_the_year_2019;
	}

	public List<ResidencyDetailsforStates> getResidencyDetailsforStates() {
		return residencyDetailsforStates;
	}

	public void setResidencyDetailsforStates(List<ResidencyDetailsforStates> residencyDetailsforStates) {
		this.residencyDetailsforStates = residencyDetailsforStates;
	}

	public boolean isCheck_if_you_and_your_spouse_are_working() {
		return check_if_you_and_your_spouse_are_working;
	}

	public void setCheck_if_you_and_your_spouse_are_working(boolean check_if_you_and_your_spouse_are_working) {
		this.check_if_you_and_your_spouse_are_working = check_if_you_and_your_spouse_are_working;
	}

	public boolean isCheck_if_you_provided_more_than_50PER_support_during_the_year_2019() {
		return check_if_you_provided_more_than_50PER_support_during_the_year_2019;
	}

	public void setCheck_if_you_provided_more_than_50PER_support_during_the_year_2019(
			boolean check_if_you_provided_more_than_50PER_support_during_the_year_2019) {
		this.check_if_you_provided_more_than_50PER_support_during_the_year_2019 = check_if_you_provided_more_than_50PER_support_during_the_year_2019;
	}

}
