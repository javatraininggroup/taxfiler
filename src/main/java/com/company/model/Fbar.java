package com.company.model;

public class Fbar {

	private String transferToForeignAccount;

	private String accBelongsTo;

	private String bankName;

	private String bankAddress;

	private String city;
	
	private String state;

	private long pincode;

	private long maximumValueInTheAcINR;

	private long accNo;

	private String typeOfAccount;

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

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
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
