package com.company.taxfiler.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "other_Income_Informaton")
public class OtherIncomeInformaton {

	private String dividendIncome;
	private String taxableRefundsFromState;
	private String receiveAlimony;
	private String receiveAnyBusinessIncome;
	private String soldAnyStocks;
	private String expensesRelatedToThatRentalProperty;
	private String farmIncomeOrLoss;
	private String socialSecurityBenefits;
	private String otherIncome;
	private String anyGamblingWinnings;
	private String incomeFromIndia;
	private String incomeSubjectToWithholding;
	private String withdrawMoneyFromHSA;
	private String withdrawMoneyFromIRA;
	private String incomeOrLossesFromRoyalties;
	private String soldYourMainHome;
	private String receivedAnyThirdPartyPayment;
	private String commissionReceivedOrPaid;
	private String soldAnyESPP;
	private String comment;

	public String getDividendIncome() {
		return dividendIncome;
	}

	public void setDividendIncome(String dividendIncome) {
		this.dividendIncome = dividendIncome;
	}

	public String getTaxableRefundsFromState() {
		return taxableRefundsFromState;
	}

	public void setTaxableRefundsFromState(String taxableRefundsFromState) {
		this.taxableRefundsFromState = taxableRefundsFromState;
	}

	public String getReceiveAlimony() {
		return receiveAlimony;
	}

	public void setReceiveAlimony(String receiveAlimony) {
		this.receiveAlimony = receiveAlimony;
	}

	public String getReceiveAnyBusinessIncome() {
		return receiveAnyBusinessIncome;
	}

	public void setReceiveAnyBusinessIncome(String receiveAnyBusinessIncome) {
		this.receiveAnyBusinessIncome = receiveAnyBusinessIncome;
	}

	public String getSoldAnyStocks() {
		return soldAnyStocks;
	}

	public void setSoldAnyStocks(String soldAnyStocks) {
		this.soldAnyStocks = soldAnyStocks;
	}

	public String getExpensesRelatedToThatRentalProperty() {
		return expensesRelatedToThatRentalProperty;
	}

	public void setExpensesRelatedToThatRentalProperty(String expensesRelatedToThatRentalProperty) {
		this.expensesRelatedToThatRentalProperty = expensesRelatedToThatRentalProperty;
	}

	public String getFarmIncomeOrLoss() {
		return farmIncomeOrLoss;
	}

	public void setFarmIncomeOrLoss(String farmIncomeOrLoss) {
		this.farmIncomeOrLoss = farmIncomeOrLoss;
	}

	public String getSocialSecurityBenefits() {
		return socialSecurityBenefits;
	}

	public void setSocialSecurityBenefits(String socialSecurityBenefits) {
		this.socialSecurityBenefits = socialSecurityBenefits;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getAnyGamblingWinnings() {
		return anyGamblingWinnings;
	}

	public void setAnyGamblingWinnings(String anyGamblingWinnings) {
		this.anyGamblingWinnings = anyGamblingWinnings;
	}

	public String getIncomeFromIndia() {
		return incomeFromIndia;
	}

	public void setIncomeFromIndia(String incomeFromIndia) {
		this.incomeFromIndia = incomeFromIndia;
	}

	public String getIncomeSubjectToWithholding() {
		return incomeSubjectToWithholding;
	}

	public void setIncomeSubjectToWithholding(String incomeSubjectToWithholding) {
		this.incomeSubjectToWithholding = incomeSubjectToWithholding;
	}

	public String getWithdrawMoneyFromHSA() {
		return withdrawMoneyFromHSA;
	}

	public void setWithdrawMoneyFromHSA(String withdrawMoneyFromHSA) {
		this.withdrawMoneyFromHSA = withdrawMoneyFromHSA;
	}

	public String getWithdrawMoneyFromIRA() {
		return withdrawMoneyFromIRA;
	}

	public void setWithdrawMoneyFromIRA(String withdrawMoneyFromIRA) {
		this.withdrawMoneyFromIRA = withdrawMoneyFromIRA;
	}

	public String getIncomeOrLossesFromRoyalties() {
		return incomeOrLossesFromRoyalties;
	}

	public void setIncomeOrLossesFromRoyalties(String incomeOrLossesFromRoyalties) {
		this.incomeOrLossesFromRoyalties = incomeOrLossesFromRoyalties;
	}

	public String getSoldYourMainHome() {
		return soldYourMainHome;
	}

	public void setSoldYourMainHome(String soldYourMainHome) {
		this.soldYourMainHome = soldYourMainHome;
	}

	public String getReceivedAnyThirdPartyPayment() {
		return receivedAnyThirdPartyPayment;
	}

	public void setReceivedAnyThirdPartyPayment(String receivedAnyThirdPartyPayment) {
		this.receivedAnyThirdPartyPayment = receivedAnyThirdPartyPayment;
	}

	public String getCommissionReceivedOrPaid() {
		return commissionReceivedOrPaid;
	}

	public void setCommissionReceivedOrPaid(String commissionReceivedOrPaid) {
		this.commissionReceivedOrPaid = commissionReceivedOrPaid;
	}

	public String getSoldAnyESPP() {
		return soldAnyESPP;
	}

	public void setSoldAnyESPP(String soldAnyESPP) {
		this.soldAnyESPP = soldAnyESPP;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
