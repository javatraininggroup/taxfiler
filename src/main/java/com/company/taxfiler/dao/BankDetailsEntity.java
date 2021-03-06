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
@Table(name = "bank_details")
public class BankDetailsEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	//@JsonManagedReference
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

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "routing_number")
	private long routingNumber;

	@Column(name = "bank_account_number")
	private long bankAccountNumber;

	@Column(name = "bank_account_type")
	private String bankAccountTpe;
	
	@Column(name = "name_of_the_account")
	private String nameOfTheAccount;

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

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public String getNameOfTheAccount() {
		return nameOfTheAccount;
	}

	public void setNameOfTheAccount(String nameOfTheAccount) {
		this.nameOfTheAccount = nameOfTheAccount;
	}
	
	

}
