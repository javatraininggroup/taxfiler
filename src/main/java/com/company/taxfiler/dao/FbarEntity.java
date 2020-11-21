package com.company.taxfiler.dao;

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
@Table(name = "fbar")
public class FbarEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	// @JsonManagedReference
	@JsonBackReference
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "transfer_to_foreign_account")
	private String transferToForeignAccount;

	@Column(name = "acc_belongs_to")
	private String accBelongsTo;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_address")
	private String bankAddress;

	private String city;
	private String state;

	@Column(name = "pincode")
	private int pinCode;

	@Column(name = "maximum_value_in_acc")
	private String maximumValueInTheAcINR;

	@Column(name = "acc_no")
	private String accNo;

	@Column(name = "type_of_account")
	private String typeOfAccount;

	private String ownership;
	@Column(name = "street_address")
	private String streetAddress;
	@Column(name = "account_maintenance_currency")
	private String accountMaintenanceCurrency;
	@Column(name = "joint_owner_name")
	private String jointOwnerName;
	@Column(name = "any_income_earned_in_XX")
	private boolean anyIncomeEarnedInXX;
	@Column(name = "income_earned_details")
	private String incomeEarnedInXXDetails;
	@Column(name = "max_value")
	private String maxValue;
	@Column(name = "value_of_account")
	private String valueOfAccount;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getTransferToForeignAccount() {
		return transferToForeignAccount;
	}

	public void setTransferToForeignAccount(String transferToForeignAccount) {
		this.transferToForeignAccount = transferToForeignAccount;
	}

	public String getAccBelongsTo() {
		return accBelongsTo;
	}

	public void setAccBelongsTo(String accBelongsTo) {
		this.accBelongsTo = accBelongsTo;
	}

	public String getMaximumValueInTheAcINR() {
		return maximumValueInTheAcINR;
	}

	public void setMaximumValueInTheAcINR(String maximumValueInTheAcINR) {
		this.maximumValueInTheAcINR = maximumValueInTheAcINR;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAccountMaintenanceCurrency() {
		return accountMaintenanceCurrency;
	}

	public void setAccountMaintenanceCurrency(String accountMaintenanceCurrency) {
		this.accountMaintenanceCurrency = accountMaintenanceCurrency;
	}

	public String getJointOwnerName() {
		return jointOwnerName;
	}

	public void setJointOwnerName(String jointOwnerName) {
		this.jointOwnerName = jointOwnerName;
	}

	public boolean isAnyIncomeEarnedInXX() {
		return anyIncomeEarnedInXX;
	}

	public void setAnyIncomeEarnedInXX(boolean anyIncomeEarnedInXX) {
		this.anyIncomeEarnedInXX = anyIncomeEarnedInXX;
	}

	public String getIncomeEarnedInXXDetails() {
		return incomeEarnedInXXDetails;
	}

	public void setIncomeEarnedInXXDetails(String incomeEarnedInXXDetails) {
		this.incomeEarnedInXXDetails = incomeEarnedInXXDetails;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getValueOfAccount() {
		return valueOfAccount;
	}

	public void setValueOfAccount(String valueOfAccount) {
		this.valueOfAccount = valueOfAccount;
	}

}
