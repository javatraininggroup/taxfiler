package com.company.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TaxPayer {
	private BasicInformation basicInformation;
	private ContactDetails contactDetails;
	private SpouseDetails spouseDetails;
	private DependentInformation dependentInformation;
	private BankDetails bankDetails;
	private OtherIncomeInfoModel otherIncomeInfoModel;
	private AdditionalInfoModel additionalInfoModel;
	private Fbar fbar;
	private OtherInformation otherInformation;

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

	public OtherIncomeInfoModel getOtherIncomeInfoModel() {
		return otherIncomeInfoModel;
	}

	public void setOtherIncomeInfoModel(OtherIncomeInfoModel otherIncomeInfoModel) {
		this.otherIncomeInfoModel = otherIncomeInfoModel;
	}

	public AdditionalInfoModel getAdditionalInfoModel() {
		return additionalInfoModel;
	}

	public void setAdditionalInfoModel(AdditionalInfoModel additionalInfoModel) {
		this.additionalInfoModel = additionalInfoModel;
	}

	public Fbar getFbar() {
		return fbar;
	}

	public void setFbar(Fbar fbar) {
		this.fbar = fbar;
	}

	public OtherInformation getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(OtherInformation otherInformation) {
		this.otherInformation = otherInformation;
	}

}
