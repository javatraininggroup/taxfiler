package com.company.model;

import java.io.Serializable;

public class RentalIncomeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double buildingValue;
	private double landValue;
	private String addressOfProperty;
	private int noOfDaysRentedDuringTheYear;
	private String dateOfPropertyPurchased;
	private String propertyHolder;
	private double income;
	private double rentsReceived;
	private double royaltiesReceived;
	private double expenses;
	private double mortgageInterest;
	private double otherInterest;
	private double insurance;
	private double repairs;
	private double autoAndTravel;
	private double advertising;
	private double taxes;
	private double legalAndOtherProfessionalTaxes;
	private double cleaningAndMaintenance;
	private double commissions;
	private double utilities;
	private double managementFees;
	private double supplies;
	private double otherExpenses;

	public double getBuildingValue() {
		return buildingValue;
	}

	public void setBuildingValue(double buildingValue) {
		this.buildingValue = buildingValue;
	}

	public double getLandValue() {
		return landValue;
	}

	public void setLandValue(double landValue) {
		this.landValue = landValue;
	}

	public String getAddressOfProperty() {
		return addressOfProperty;
	}

	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
	}

	public int getNoOfDaysRentedDuringTheYear() {
		return noOfDaysRentedDuringTheYear;
	}

	public void setNoOfDaysRentedDuringTheYear(int noOfDaysRentedDuringTheYear) {
		this.noOfDaysRentedDuringTheYear = noOfDaysRentedDuringTheYear;
	}

	public String getDateOfPropertyPurchased() {
		return dateOfPropertyPurchased;
	}

	public void setDateOfPropertyPurchased(String dateOfPropertyPurchased) {
		this.dateOfPropertyPurchased = dateOfPropertyPurchased;
	}

	public String getPropertyHolder() {
		return propertyHolder;
	}

	public void setPropertyHolder(String propertyHolder) {
		this.propertyHolder = propertyHolder;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getRentsReceived() {
		return rentsReceived;
	}

	public void setRentsReceived(double rentsReceived) {
		this.rentsReceived = rentsReceived;
	}

	public double getRoyaltiesReceived() {
		return royaltiesReceived;
	}

	public void setRoyaltiesReceived(double royaltiesReceived) {
		this.royaltiesReceived = royaltiesReceived;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getMortgageInterest() {
		return mortgageInterest;
	}

	public void setMortgageInterest(double mortgageInterest) {
		this.mortgageInterest = mortgageInterest;
	}

	public double getOtherInterest() {
		return otherInterest;
	}

	public void setOtherInterest(double otherInterest) {
		this.otherInterest = otherInterest;
	}

	public double getInsurance() {
		return insurance;
	}

	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}

	public double getRepairs() {
		return repairs;
	}

	public void setRepairs(double repairs) {
		this.repairs = repairs;
	}

	public double getAutoAndTravel() {
		return autoAndTravel;
	}

	public void setAutoAndTravel(double autoAndTravel) {
		this.autoAndTravel = autoAndTravel;
	}

	public double getAdvertising() {
		return advertising;
	}

	public void setAdvertising(double advertising) {
		this.advertising = advertising;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getLegalAndOtherProfessionalTaxes() {
		return legalAndOtherProfessionalTaxes;
	}

	public void setLegalAndOtherProfessionalTaxes(double legalAndOtherProfessionalTaxes) {
		this.legalAndOtherProfessionalTaxes = legalAndOtherProfessionalTaxes;
	}

	public double getCleaningAndMaintenance() {
		return cleaningAndMaintenance;
	}

	public void setCleaningAndMaintenance(double cleaningAndMaintenance) {
		this.cleaningAndMaintenance = cleaningAndMaintenance;
	}

	public double getCommissions() {
		return commissions;
	}

	public void setCommissions(double commissions) {
		this.commissions = commissions;
	}

	public double getUtilities() {
		return utilities;
	}

	public void setUtilities(double utilities) {
		this.utilities = utilities;
	}

	public double getManagementFees() {
		return managementFees;
	}

	public void setManagementFees(double managementFees) {
		this.managementFees = managementFees;
	}

	public double getSupplies() {
		return supplies;
	}

	public void setSupplies(double supplies) {
		this.supplies = supplies;
	}

	public double getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

}
