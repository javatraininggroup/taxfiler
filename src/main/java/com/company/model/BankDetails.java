package com.company.model;

public class BankDetails {

	private String nameOfTheBank;
	private long bankRoutingNumber;
	private Long usBankAccountNumber;
	private String typeOfAccount;

	public String getNameOfTheBank() {
		return nameOfTheBank;
	}

	public void setNameOfTheBank(String nameOfTheBank) {
		this.nameOfTheBank = nameOfTheBank;
	}

	public long getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	public void setBankRoutingNumber(long bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

	public Long getUSBankAccountNumber() {
		return usBankAccountNumber;
	}

	public void setUSBankAccountNumber(Long usBankAccountNumber) {
		this.usBankAccountNumber = usBankAccountNumber;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

}
