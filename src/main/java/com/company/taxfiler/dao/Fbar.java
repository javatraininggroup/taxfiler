package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fbar")
public class Fbar {
	private long id;
	
	@Column(name = "tax_filed_year")
	private long taxFiledYearId;
	
	@Column(name="did_you_transfer")
	private String transferTenThousandToForeignAcc;
	
	@Column(name="acc_belongs_to")
	private String acBelongsTo;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_address")
	private String bankAddress;
	
	private String city;
	private String state;
	
	@Column(name="pin_code")
	private long pinCode;

	@Column(name="maximum_value_in_acc")
	private long MaximumValueInTheAcINR;
	
	@Column(name="acc_no")
	private long acNo;
	
	@Column(name="type_of_account")
	private String TypeOfAccount;
	
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

	public String getTransferTenThousandToForeignAcc() {
		return transferTenThousandToForeignAcc;
	}

	public void setTransferTenThousandToForeignAcc(String transferTenThousandToForeignAcc) {
		this.transferTenThousandToForeignAcc = transferTenThousandToForeignAcc;
	}

	public String getAcBelongsTo() {
		return acBelongsTo;
	}

	public void setAcBelongsTo(String acBelongsTo) {
		this.acBelongsTo = acBelongsTo;
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

	public long getMaximumValueInTheAcINR() {
		return MaximumValueInTheAcINR;
	}

	public void setMaximumValueInTheAcINR(long maximumValueInTheAcINR) {
		MaximumValueInTheAcINR = maximumValueInTheAcINR;
	}

	public long getAcNo() {
		return acNo;
	}

	public void setAcNo(long acNo) {
		this.acNo = acNo;
	}

	public String getTypeOfAccount() {
		return TypeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		TypeOfAccount = typeOfAccount;
	}

	
}
