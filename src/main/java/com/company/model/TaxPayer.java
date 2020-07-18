package com.company.model;

public class TaxPayer {
	private BasicInformation basicInformation;
	private ContactDetails contactDetails;
	private SpouseDetails spouseDetails;
	private DependentInformation dependentInformation;
	private BankDetails bankDetails;

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public SpouseDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(SpouseDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	public DependentInformation getDependentInformation() {
		return dependentInformation;
	}

	public void setDependentInformation(DependentInformation dependentInformation) {
		this.dependentInformation = dependentInformation;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

}
