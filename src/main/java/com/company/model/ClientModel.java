package com.company.model;

import java.util.Set;

public class ClientModel {

	private ClientDetails clientDetails;
	private BasicInformation basicInformation;
	private ContactDetails contactDetails;
	private SpouseDetails spouseDetails;
	private Set<DependentInformation> dependentInformation;
	private BankDetails bankDetails;
	private OtherIncomeInfoModel otherIncomeInfoModel;
	private AdditionalInfoModel additionalInfoModel;
	private Fbar fbar;
	private OtherInformation otherInformation;
	// private RentalIncomeEntity rentalIncomeModel;
	private RentalIncomeModel rentalIncomeModel;
	private ExpensesAndConntributionResponseModel expensesAndContributionModel;

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
	}

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

	public Set<DependentInformation> getDependentInformation() {
		return dependentInformation;
	}

	public void setDependentInformation(Set<DependentInformation> dependentInformation) {
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

	public RentalIncomeModel getRentalIncomeModel() {
		return rentalIncomeModel;
	}

	public void setRentalIncomeModel(RentalIncomeModel rentalIncomeModel) {
		this.rentalIncomeModel = rentalIncomeModel;
	}

	public ExpensesAndConntributionResponseModel getExpensesAndContributionModel() {
		return expensesAndContributionModel;
	}

	public void setExpensesAndContributionModel(ExpensesAndConntributionResponseModel expensesAndContributionModel) {
		this.expensesAndContributionModel = expensesAndContributionModel;
	}

}
