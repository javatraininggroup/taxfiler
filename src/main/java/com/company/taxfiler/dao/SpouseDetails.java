package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spouse_details")
public class SpouseDetails {

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;

	private Date dob;
	private long ssn;

	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;

	@Column(name = "check_if_itin_to_be_applied")
	private boolean checkifITINtobeApplied;
	@Column(name = "check_if_itin_to_be_renewed")
	private boolean checkifITINtobeRenewed;
	@Column(name = "itin_renewed")
	private boolean ITITrenewed;
	@Column(name = "entry_date_info")
	private Date entryDateInfo;
	private String occupation;
	@Column(name = "check_if_lived_more_then_6_months")
	private boolean checkifLivedForMoreThan06MonthsWithYouInUS;
	private long id;
	@Column(name = "did_your_spouse_worked_in")
	private boolean didYourSpouseWorkedIn;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isCheckifLivedForMoreThan06MonthsWithYouInUS() {
		return checkifLivedForMoreThan06MonthsWithYouInUS;
	}

	public void setCheckifLivedForMoreThan06MonthsWithYouInUS(boolean checkifLivedForMoreThan06MonthsWithYouInUS) {
		this.checkifLivedForMoreThan06MonthsWithYouInUS = checkifLivedForMoreThan06MonthsWithYouInUS;
	}

	public boolean isDidYourSpouseWorkedIn() {
		return didYourSpouseWorkedIn;
	}

	public void setDidYourSpouseWorkedIn(boolean didYourSpouseWorkedIn) {
		this.didYourSpouseWorkedIn = didYourSpouseWorkedIn;
	}

}
