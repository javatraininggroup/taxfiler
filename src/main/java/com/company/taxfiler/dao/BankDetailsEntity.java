package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Bank_Details")

public class BankDetailsEntity {

	private long id;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "routing_number")
	private long routingNumber;

	@Column(name = "bank_account_number")
	private long bankAccountNumber;

	@Column(name = "bank_account_type")
	private String bankAccountTpe;

	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(long routingNumber) {
		this.routingNumber = routingNumber;
	}

	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountTpe() {
		return bankAccountTpe;
	}

	public void setBankAccountTpe(String bankAccountTpe) {
		this.bankAccountTpe = bankAccountTpe;
	}

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

}
