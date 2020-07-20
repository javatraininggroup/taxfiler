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
	
	private boolean checkifITINtobeApplied;
	private boolean checkifITINtobeRenewed;
	private boolean ITITrenewed;
	private Date entryDateInfo;
	private String occupation;
	private boolean CheckifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019;
	private long id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}
	public long getTaxFiledYearId() {
		return taxFiledYearId;
	}
	public void setTaxFiledYearId(long taxFiledYearId) {
		this.taxFiledYearId = taxFiledYearId;
	}
	public boolean isCheckifITINtobeApplied() {
		return checkifITINtobeApplied;
	}
	public void setCheckifITINtobeApplied(boolean checkifITINtobeApplied) {
		this.checkifITINtobeApplied = checkifITINtobeApplied;
	}
	public boolean isCheckifITINtobeRenewed() {
		return checkifITINtobeRenewed;
	}
	public void setCheckifITINtobeRenewed(boolean checkifITINtobeRenewed) {
		this.checkifITINtobeRenewed = checkifITINtobeRenewed;
	}
	public boolean isITITrenewed() {
		return ITITrenewed;
	}
	public void setITITrenewed(boolean iTITrenewed) {
		ITITrenewed = iTITrenewed;
	}
	public Date getEntryDateInfo() {
		return entryDateInfo;
	}
	public void setEntryDateInfo(Date entryDateInfo) {
		this.entryDateInfo = entryDateInfo;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public boolean isCheckifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019() {
		return CheckifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019;
	}
	public void setCheckifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019(
			boolean checkifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019) {
		CheckifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019 = checkifLivedForMoreThan06MonthsWithYouInUSduringTheYear2019;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

}
