package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spouse_details")
public class SpouseDetails {

	private String name;

	private Date dob;
	private long ssn;

	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;

}
