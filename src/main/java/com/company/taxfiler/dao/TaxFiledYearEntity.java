package com.company.taxfiler.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tax_file_year")
public class TaxFiledYearEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int year;

	@Column(name = "main_status")
	private String mainStatus;

	@Column(name = "sub_status")
	private String subStatus;

	@PrePersist
	protected void onCreate() {
		if (mainStatus == null) {
			mainStatus = "SCHEDULING";
		}
		if (subStatus == null) {
			subStatus = "NEW_REGISTER";
		}
	}

	@PreUpdate
	protected void onUpdate() {
		if (mainStatus == null) {
			mainStatus = "SCHEDULING";
		}
		if (subStatus == null) {
			subStatus = "NEW_REGISTER";
		}
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id", referencedColumnName = "id")
	// @JsonManagedReference
	@JsonBackReference
	private UserEntity userEntity;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private BasicInfoEntity basicInfo;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private ContactDetailsEntity contactDetails;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private SpouseDetailsEntity spouseDetails;

	/*
	 * @OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch =
	 * FetchType.LAZY, cascade = CascadeType.ALL) // @JsonBackReference
	 * 
	 * @JsonManagedReference private DependentInformationEntity
	 * dependentInformation;
	 */

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<DependentInformationEntity> dependentInformationList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private BankDetailsEntity bankDetails;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<AdditionalInformationEntity> additionalInformationEntityList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private FbarEntity fbarEntity;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private OtherInformationEntity otherInformationEntity;

	@OneToMany(/* orphanRemoval = true, */ mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<UploadFilesEntity> UploadFilesEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<MessagesEntity> messagesEntityList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private RentalIncomeEntity rentalIncome;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<ExpensesEntity> expensesEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<ContributionEntity> contributionEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonBackReference
	@JsonManagedReference
	private Set<CommentsEntity> commentsList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public BasicInfoEntity getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(BasicInfoEntity basicInfo) {
		this.basicInfo = basicInfo;
	}

	public ContactDetailsEntity getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetailsEntity contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Set<ResidencyDetailsForStatesEntity> getResidencyDetailsforStatesList() {
		if (null == residencyDetailsforStatesList) {
			residencyDetailsforStatesList = new HashSet<>();
		}
		return residencyDetailsforStatesList;
	}

	public SpouseDetailsEntity getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(SpouseDetailsEntity spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	/*
	 * public DependentInformationEntity getDependentInformation() { return
	 * dependentInformation; }
	 * 
	 * public void setDependentInformation(DependentInformationEntity
	 * dependentInformation) { this.dependentInformation = dependentInformation; }
	 */

	public BankDetailsEntity getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetailsEntity bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Set<OtherIncomeInformatonEntity> getOtherIncomeInformatonEntityList() {
		return otherIncomeInformatonEntityList;
	}

	public void setOtherIncomeInformatonEntityList(Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntityList) {
		this.otherIncomeInformatonEntityList = otherIncomeInformatonEntityList;
	}

	public Set<AdditionalInformationEntity> getAdditionalInformationEntityList() {
		return additionalInformationEntityList;
	}

	public void setAdditionalInformationEntityList(Set<AdditionalInformationEntity> additionalInformationEntityList) {
		this.additionalInformationEntityList = additionalInformationEntityList;
	}

	public Set<UploadFilesEntity> getUploadFilesEntityList() {
		return UploadFilesEntityList;
	}

	public void setUploadFilesEntityList(Set<UploadFilesEntity> uploadFilesEntityList) {
		UploadFilesEntityList = uploadFilesEntityList;
	}

	public void setResidencyDetailsforStatesList(Set<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList) {
		this.residencyDetailsforStatesList = residencyDetailsforStatesList;
	}

	public FbarEntity getFbarEntity() {
		return fbarEntity;
	}

	public void setFbarEntity(FbarEntity fbarEntity) {
		this.fbarEntity = fbarEntity;
	}

	public OtherInformationEntity getOtherInformationEntity() {
		return otherInformationEntity;
	}

	public void setOtherInformationEntity(OtherInformationEntity otherInformationEntity) {
		this.otherInformationEntity = otherInformationEntity;
	}

	public Set<MessagesEntity> getMessagesEntityList() {
		return messagesEntityList;
	}

	public void setMessagesEntityList(Set<MessagesEntity> messagesEntityList) {
		this.messagesEntityList = messagesEntityList;
	}

	public String getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public RentalIncomeEntity getRentalIncome() {
		return rentalIncome;
	}

	public void setRentalIncome(RentalIncomeEntity rentalIncome) {
		this.rentalIncome = rentalIncome;
	}

	public Set<ExpensesEntity> getExpensesEntityList() {
		return expensesEntityList;
	}

	public void setExpensesEntityList(Set<ExpensesEntity> expensesEntityList) {
		this.expensesEntityList = expensesEntityList;
	}

	public Set<ContributionEntity> getContributionEntityList() {
		return contributionEntityList;
	}

	public void setContributionEntityList(Set<ContributionEntity> contributionEntityList) {
		this.contributionEntityList = contributionEntityList;
	}

	public Set<DependentInformationEntity> getDependentInformationList() {
		return dependentInformationList;
	}

	public void setDependentInformationList(Set<DependentInformationEntity> dependentInformationList) {
		this.dependentInformationList = dependentInformationList;
	}

	public Set<CommentsEntity> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(Set<CommentsEntity> commentsList) {
		this.commentsList = commentsList;
	}

}
