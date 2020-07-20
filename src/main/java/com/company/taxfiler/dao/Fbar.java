package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FBAR")
public class Fbar {
	private long id;
	
	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;
	
	@Column(name="did_you_transfer/Maintained_$10,000_or_more_to_foreign_Account")
	private String transferTenThousandToForeignAcc;
	
	@Column(name="a/c_Belongs_to")
	private String acBelongsTo;
	
	@Column(name="bank_Name")
	private String bankName;
	
	@Column(name="bank_Address")
	private String bankAddress;
	
	private String city;
	private String state;
	
	@Column(name="pin_code")
	private long pinCode;

	@Column(name="maximum_value_in_the_a/c_INR")
	private long MaximumValueInTheAcINR;
	
	@Column(name="a/c_No")
	private long acNo;
	
	@Column(name="type_of_account")
	private String TypeOfAccount;
}
