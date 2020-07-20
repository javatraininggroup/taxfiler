package com.company.taxfiler.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "additional_Information")
public class AdditionalInformation {

	private String medicalExpenses;
	private String HomeMortgageInterestInUSOrIndia;
	private String didYouMakeAnyCharitableContributions;
	private String anyCasualtyOrTheftLosses;
	private String doYouMakeAnyAdditionalContributions;
	private String doYouHaveStudentLoan;
	private String payAnyTuitionFee;
	private String itemizeYourReturnsLastYear;
	private String foreignBankAccount;
	private String familyCoveredWithHealthInsurance;
	private String healthInsuranceWasProvidedByEmployer;
	private String paidAlimonyIn_2019;
	private String SalesAndExciseTaxesOnVehicleBoughtIn_2019;
	private String energySavingProductInUSA;
	private String workedMoreThanOneEmployerIn_2019;
	private String foreignAccountThanYouNeedToReportFATCA;
	private String paidAnyEstimatedTaxes;
	private String filedLastYearTaxReturnWithBesttaxfiler;
	private String purchasedAnyHybridMotorVehicle;
	private String appliedITIN_in_2014_with_2013;
	private String taxPreparationFeeOfLastYear;
	private String safeDepositRentals;
	private String comment;

	public String getMedicalExpenses() {
		return medicalExpenses;
	}

	public void setMedicalExpenses(String medicalExpenses) {
		this.medicalExpenses = medicalExpenses;
	}

	public String getHomeMortgageInterestInUSOrIndia() {
		return HomeMortgageInterestInUSOrIndia;
	}

	public void setHomeMortgageInterestInUSOrIndia(String homeMortgageInterestInUSOrIndia) {
		HomeMortgageInterestInUSOrIndia = homeMortgageInterestInUSOrIndia;
	}

	public String getDidYouMakeAnyCharitableContributions() {
		return didYouMakeAnyCharitableContributions;
	}

	public void setDidYouMakeAnyCharitableContributions(String didYouMakeAnyCharitableContributions) {
		this.didYouMakeAnyCharitableContributions = didYouMakeAnyCharitableContributions;
	}

	public String getAnyCasualtyOrTheftLosses() {
		return anyCasualtyOrTheftLosses;
	}

	public void setAnyCasualtyOrTheftLosses(String anyCasualtyOrTheftLosses) {
		this.anyCasualtyOrTheftLosses = anyCasualtyOrTheftLosses;
	}

	public String getDoYouMakeAnyAdditionalContributions() {
		return doYouMakeAnyAdditionalContributions;
	}

	public void setDoYouMakeAnyAdditionalContributions(String doYouMakeAnyAdditionalContributions) {
		this.doYouMakeAnyAdditionalContributions = doYouMakeAnyAdditionalContributions;
	}

	public String getDoYouHaveStudentLoan() {
		return doYouHaveStudentLoan;
	}

	public void setDoYouHaveStudentLoan(String doYouHaveStudentLoan) {
		this.doYouHaveStudentLoan = doYouHaveStudentLoan;
	}

	public String getPayAnyTuitionFee() {
		return payAnyTuitionFee;
	}

	public void setPayAnyTuitionFee(String payAnyTuitionFee) {
		this.payAnyTuitionFee = payAnyTuitionFee;
	}

	public String getItemizeYourReturnsLastYear() {
		return itemizeYourReturnsLastYear;
	}

	public void setItemizeYourReturnsLastYear(String itemizeYourReturnsLastYear) {
		this.itemizeYourReturnsLastYear = itemizeYourReturnsLastYear;
	}

	public String getForeignBankAccount() {
		return foreignBankAccount;
	}

	public void setForeignBankAccount(String foreignBankAccount) {
		this.foreignBankAccount = foreignBankAccount;
	}

	public String getFamilyCoveredWithHealthInsurance() {
		return familyCoveredWithHealthInsurance;
	}

	public void setFamilyCoveredWithHealthInsurance(String familyCoveredWithHealthInsurance) {
		this.familyCoveredWithHealthInsurance = familyCoveredWithHealthInsurance;
	}

	public String getHealthInsuranceWasProvidedByEmployer() {
		return healthInsuranceWasProvidedByEmployer;
	}

	public void setHealthInsuranceWasProvidedByEmployer(String healthInsuranceWasProvidedByEmployer) {
		this.healthInsuranceWasProvidedByEmployer = healthInsuranceWasProvidedByEmployer;
	}

	public String getPaidAlimonyIn_2019() {
		return paidAlimonyIn_2019;
	}

	public void setPaidAlimonyIn_2019(String paidAlimonyIn_2019) {
		this.paidAlimonyIn_2019 = paidAlimonyIn_2019;
	}

	public String getSalesAndExciseTaxesOnVehicleBoughtIn_2019() {
		return SalesAndExciseTaxesOnVehicleBoughtIn_2019;
	}

	public void setSalesAndExciseTaxesOnVehicleBoughtIn_2019(String salesAndExciseTaxesOnVehicleBoughtIn_2019) {
		SalesAndExciseTaxesOnVehicleBoughtIn_2019 = salesAndExciseTaxesOnVehicleBoughtIn_2019;
	}

	public String getEnergySavingProductInUSA() {
		return energySavingProductInUSA;
	}

	public void setEnergySavingProductInUSA(String energySavingProductInUSA) {
		this.energySavingProductInUSA = energySavingProductInUSA;
	}

	public String getWorkedMoreThanOneEmployerIn_2019() {
		return workedMoreThanOneEmployerIn_2019;
	}

	public void setWorkedMoreThanOneEmployerIn_2019(String workedMoreThanOneEmployerIn_2019) {
		this.workedMoreThanOneEmployerIn_2019 = workedMoreThanOneEmployerIn_2019;
	}

	public String getForeignAccountThanYouNeedToReportFATCA() {
		return foreignAccountThanYouNeedToReportFATCA;
	}

	public void setForeignAccountThanYouNeedToReportFATCA(String foreignAccountThanYouNeedToReportFATCA) {
		this.foreignAccountThanYouNeedToReportFATCA = foreignAccountThanYouNeedToReportFATCA;
	}

	public String getPaidAnyEstimatedTaxes() {
		return paidAnyEstimatedTaxes;
	}

	public void setPaidAnyEstimatedTaxes(String paidAnyEstimatedTaxes) {
		this.paidAnyEstimatedTaxes = paidAnyEstimatedTaxes;
	}

	public String getFiledLastYearTaxReturnWithBesttaxfiler() {
		return filedLastYearTaxReturnWithBesttaxfiler;
	}

	public void setFiledLastYearTaxReturnWithBesttaxfiler(String filedLastYearTaxReturnWithBesttaxfiler) {
		this.filedLastYearTaxReturnWithBesttaxfiler = filedLastYearTaxReturnWithBesttaxfiler;
	}

	public String getPurchasedAnyHybridMotorVehicle() {
		return purchasedAnyHybridMotorVehicle;
	}

	public void setPurchasedAnyHybridMotorVehicle(String purchasedAnyHybridMotorVehicle) {
		this.purchasedAnyHybridMotorVehicle = purchasedAnyHybridMotorVehicle;
	}

	public String getAppliedITIN_in_2014_with_2013() {
		return appliedITIN_in_2014_with_2013;
	}

	public void setAppliedITIN_in_2014_with_2013(String appliedITIN_in_2014_with_2013) {
		this.appliedITIN_in_2014_with_2013 = appliedITIN_in_2014_with_2013;
	}

	public String getTaxPreparationFeeOfLastYear() {
		return taxPreparationFeeOfLastYear;
	}

	public void setTaxPreparationFeeOfLastYear(String taxPreparationFeeOfLastYear) {
		this.taxPreparationFeeOfLastYear = taxPreparationFeeOfLastYear;
	}

	public String getSafeDepositRentals() {
		return safeDepositRentals;
	}

	public void setSafeDepositRentals(String safeDepositRentals) {
		this.safeDepositRentals = safeDepositRentals;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
