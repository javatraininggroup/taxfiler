package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Bank_Details")
public class BankDetails {
	
@Column(name="bank_name")	
private String bankName;

@Column(name="routing_number")
private long routingNumber;

@Column(name="bank_account_number")
private long bankAccountNumber;

@Column(name="bank_account_type")
private String bankAccountTpe;
private long id;
@Column(name="tax_filed_year_id")
private long taxFiledYearId;


}
