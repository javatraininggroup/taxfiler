package com.company.taxfiler.dao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tax_file_year")
public class TaxFiledYearEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int year;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id", referencedColumnName = "id")
	private UserEntity userEntity;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BasicInfoEntity basicInfo;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ContactDetailsEntity contactDetails;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private SpouseDetailsEntity spouseDetails;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DependentInformationEntity dependentInformation;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private BankDetailsEntity bankDetails;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<AdditionalInformationEntity> additionalInformationEntityList;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private FbarEntity fbarEntity;

	@OneToOne(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private OtherInformationEntity otherInformationEntity;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UploadFilesEntity> UploadFilesEntityList;

	@OneToMany(orphanRemoval = true, mappedBy = "taxFileYear", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MessagesEntity> messagesEntityList;

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
		return residencyDetailsforStatesList;
	}

	public SpouseDetailsEntity getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(SpouseDetailsEntity spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	public DependentInformationEntity getDependentInformation() {
		return dependentInformation;
	}

	public void setDependentInformation(DependentInformationEntity dependentInformation) {
		this.dependentInformation = dependentInformation;
	}

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

}
