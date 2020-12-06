package com.company.taxfiler.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "rental_income")
public class RentalIncomeEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	// @JsonManagedReference
	@JsonBackReference
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "building_value")
	private double buildingValue;
	@Column(name = "land_value")
	private double landValue;
	@Column(name = "address_of_property")
	private String addressOfProperty;
	@Column(name = "no_of_days_rented_during_year")
	private int noOfDaysRentedDuringTheYear;
	@Column(name = "date_of_property_purchased")
	private Date dateOfPropertyPurchased;
	@Column(name = "property_holder")
	private String propertyHolder;
	private double income;
	@Column(name = "rents_received")
	private double rentsReceived;
	@Column(name = "royalties_received")
	private double royaltiesReceived;
	private double expenses;
	@Column(name = "mortgage_interest")
	private double mortgageInterest;
	@Column(name = "other_interest")
	private double otherInterest;
	private double insurance;
	private double repairs;
	@Column(name = "auto_and_travel")
	private double autoAndTravel;
	private double advertising;
	private double taxes;
	@Column(name = "legal_and_other_professional_taxes")
	private double legalAndOtherProfessionalTaxes;
	@Column(name = "cleaning_and_maintenance")
	private double cleaningAndMaintenance;
	private double commissions;
	private double utilities;
	@Column(name = "management_fees")
	private double managementFees;
	private double supplies;
	@Column(name = "other_expenses")
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

	public Date getDateOfPropertyPurchased() {
		return dateOfPropertyPurchased;
	}

	public void setDateOfPropertyPurchased(Date dateOfPropertyPurchased) {
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

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
