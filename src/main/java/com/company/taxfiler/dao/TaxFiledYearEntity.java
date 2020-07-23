package com.company.taxfiler.dao;

import java.util.List;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "users_id", nullable = false)
	private UserEntity userEntity;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BasicInfoEntity basicInfo;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ContactDetailsEntity contactDetails;

	@OneToMany(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private SpouseDetailsEntity spouseDetails;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DependentInformationEntity dependentInformation;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BankDetailsEntity bankDetails;

	@OneToMany(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OtherIncomeInformatonEntity> otherIncomeInformatonEntityList;

	@OneToMany(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AdditionalInformationEntity> additionalInformationEntityList;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FbarEntity fbarEntity;

	@OneToOne(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private OtherInformationEntity otherInformationEntity;

	@OneToMany(mappedBy = "taxFileYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UploadFilesEntity> UploadFilesEntityList;

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

	public List<ResidencyDetailsForStatesEntity> getResidencyDetailsforStatesList() {
		return residencyDetailsforStatesList;
	}

	public void setResidencyDetailsforStatesList(List<ResidencyDetailsForStatesEntity> residencyDetailsforStatesList) {
		this.residencyDetailsforStatesList = residencyDetailsforStatesList;
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

	public List<OtherIncomeInformatonEntity> getOtherIncomeInformatonEntityList() {
		return otherIncomeInformatonEntityList;
	}

	public void setOtherIncomeInformatonEntityList(List<OtherIncomeInformatonEntity> otherIncomeInformatonEntityList) {
		this.otherIncomeInformatonEntityList = otherIncomeInformatonEntityList;
	}

	public List<AdditionalInformationEntity> getAdditionalInformationEntityList() {
		return additionalInformationEntityList;
	}

	public void setAdditionalInformationEntityList(List<AdditionalInformationEntity> additionalInformationEntityList) {
		this.additionalInformationEntityList = additionalInformationEntityList;
	}

	public List<UploadFilesEntity> getUploadFilesEntityList() {
		return UploadFilesEntityList;
	}

	public void setUploadFilesEntityList(List<UploadFilesEntity> uploadFilesEntityList) {
		UploadFilesEntityList = uploadFilesEntityList;
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

}
