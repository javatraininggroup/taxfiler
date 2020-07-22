package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fbar")
public class FbarEntity {
	private long id;

	@Column(name = "tax_filed_year")
	private long taxFiledYearId;

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
	private long pinCode;

	@Column(name = "maximum_value_in_acc")
	private long maximumValueInTheAcINR;

	@Column(name = "acc_no")
	private long accNo;

	@Column(name = "type_of_account")
	private String typeOfAccount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTaxFiledYearId() {
		return taxFiledYearId;
	}

	public void setTaxFiledYearId(long taxFiledYearId) {
		this.taxFiledYearId = taxFiledYearId;
	}

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

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
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

	public long getMaximumValueInTheAcINR() {
		return maximumValueInTheAcINR;
	}

	public void setMaximumValueInTheAcINR(long maximumValueInTheAcINR) {
		this.maximumValueInTheAcINR = maximumValueInTheAcINR;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

}
