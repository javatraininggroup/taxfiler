package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dependent_info")
public class DependentInformationEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
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

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "dob")
	private Date dateOfBirth;
	@Column(name = "ssn_itin")
	private String ssnitin;
	@Column(name = "check_if_itin_to_be_applied")
	private boolean checkIfITINToBeApplied;
	@Column(name = "check_if_itin_to_be_renewed")
	private boolean checkIfITINToBeRenewed;
	@Column(name = "itin_renewed")
	private boolean ITINRenewed;
	private String relationship;
	@Column(name = "visa_status")
	private String visaStatus;
	@Column(name = "lived_for_more_than_06_months")
	private boolean livedForMoreThan06Months;
	@Column(name = "provided_more_than_50PE_support")
	private boolean providedMoreThan50PESupport;
	@Column(name = "is_you_and_spouse_working")
	private boolean isYouAndSpouseWorking;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
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

	public String getSsnitin() {
		return ssnitin;
	}

	public void setSsnitin(String ssnitin) {
		this.ssnitin = ssnitin;
	}

	public boolean isCheckIfITINToBeApplied() {
		return checkIfITINToBeApplied;
	}

	public void setCheckIfITINToBeApplied(boolean checkIfITINToBeApplied) {
		this.checkIfITINToBeApplied = checkIfITINToBeApplied;
	}

	public boolean isCheckIfITINToBeRenewed() {
		return checkIfITINToBeRenewed;
	}

	public void setCheckIfITINToBeRenewed(boolean checkIfITINToBeRenewed) {
		this.checkIfITINToBeRenewed = checkIfITINToBeRenewed;
	}

	public boolean isITINRenewed() {
		return ITINRenewed;
	}

	public void setITINRenewed(boolean iTINRenewed) {
		ITINRenewed = iTINRenewed;
	}

	public boolean isLivedForMoreThan06Months() {
		return livedForMoreThan06Months;
	}

	public void setLivedForMoreThan06Months(boolean livedForMoreThan06Months) {
		this.livedForMoreThan06Months = livedForMoreThan06Months;
	}

	public boolean isProvidedMoreThan50PESupport() {
		return providedMoreThan50PESupport;
	}

	public void setProvidedMoreThan50PESupport(boolean providedMoreThan50PESupport) {
		this.providedMoreThan50PESupport = providedMoreThan50PESupport;
	}

	public boolean isYouAndSpouseWorking() {
		return isYouAndSpouseWorking;
	}

	public void setYouAndSpouseWorking(boolean isYouAndSpouseWorking) {
		this.isYouAndSpouseWorking = isYouAndSpouseWorking;
	}

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

}
