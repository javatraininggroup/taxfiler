package com.company.taxfiler.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.AdditionalInfoModel;
import com.company.model.BankDetails;
import com.company.model.BasicInformation;
import com.company.model.ContactDetails;
import com.company.model.DependentInformation;
import com.company.model.OtherIncomeInfoData;
import com.company.model.OtherIncomeInfoModel;
import com.company.model.ResidencyDetailsforStates;
import com.company.model.SpouseDetails;
import com.company.model.TaxPayer;
import com.company.model.TaxYearInfo;
import com.company.taxfiler.dao.AdditionalInformationEntity;
import com.company.taxfiler.dao.BankDetailsEntity;
import com.company.taxfiler.dao.BasicInfoEntity;
import com.company.taxfiler.dao.ContactDetailsEntity;
import com.company.taxfiler.dao.DependentInformationEntity;
import com.company.taxfiler.dao.OtherIncomeInformatonEntity;
import com.company.taxfiler.dao.ResidencyDetailsForStatesEntity;
import com.company.taxfiler.dao.SpouseDetailsEntity;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.repository.UserRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@Transactional
public class UpdateUserController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/updateuser/{user_id}/{tax_year}/basicInfo")
	public Object updateUserBasicInfo(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {

			/*
			 * TaxFiledYearEntity taxFiledYearEntity1 =
			 * taxFileRepository.findByYear(taxYear); if (taxFiledYearEntity1.getBasicInfo()
			 * == null) { System.out.println("db basic info obj is null"); }else {
			 * System.out.println("data available"); } System.exit(0);
			 */

			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				BasicInformation basicInformationModel = taxPayerModel.getBasicInformation();
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				TaxFiledYearEntity taxFiledYearEntity = null;

				BasicInfoEntity basicInfo = null;
				ContactDetailsEntity contactDetailsEntity = null;
				SpouseDetailsEntity spouseDetailsEntity = null;
				Set<ResidencyDetailsForStatesEntity> residencyDetailsForStatesEntityList = null;

				if (null == taxFiledYearEntityList || taxFiledYearEntityList.size() == 0) {
					taxFiledYearEntityList = new HashSet<>();
				} else {
					for (TaxFiledYearEntity taxFiledYearEntityTemp : taxFiledYearEntityList) {
						if (taxFiledYearEntityTemp.getYear() == taxYear) {
							taxFiledYearEntity = taxFiledYearEntityTemp;
							if (taxFiledYearEntityTemp.getBasicInfo() == null) {
								LOGGER.info("db basic info obj is null");
							}
							break;
						}
					}
				}
				if (null == taxFiledYearEntity) {
					taxFiledYearEntity = new TaxFiledYearEntity();
					taxFiledYearEntity.setYear(taxYear);

					basicInfo = new BasicInfoEntity();
					contactDetailsEntity = new ContactDetailsEntity();
					spouseDetailsEntity = new SpouseDetailsEntity();
					residencyDetailsForStatesEntityList = new HashSet<>();
				} else {
					if (taxFiledYearEntity.getBasicInfo() == null) {
						LOGGER.info("basic info obj is null");
					}
					basicInfo = taxFiledYearEntity.getBasicInfo() != null ? taxFiledYearEntity.getBasicInfo()
							: new BasicInfoEntity();
					contactDetailsEntity = taxFiledYearEntity.getContactDetails() != null
							? taxFiledYearEntity.getContactDetails()
							: new ContactDetailsEntity();
					spouseDetailsEntity = taxFiledYearEntity.getSpouseDetails() != null
							? taxFiledYearEntity.getSpouseDetails()
							: new SpouseDetailsEntity();
					residencyDetailsForStatesEntityList = taxFiledYearEntity.getResidencyDetailsforStatesList() != null
							? taxFiledYearEntity.getResidencyDetailsforStatesList()
							: new HashSet<ResidencyDetailsForStatesEntity>();
				}
				if (null != basicInformationModel) {

					basicInfo.setMartialStatus(basicInformationModel.getMartialStatus());
					basicInfo.setFirstName(basicInformationModel.getName().getFirstName());
					basicInfo.setMiddleName(basicInformationModel.getName().getMiddleName());
					basicInfo.setLastName(basicInformationModel.getName().getLastName());
					basicInfo.setSsn(basicInformationModel.getSsn());
					basicInfo.setOccupation(basicInformationModel.getOccupation());

					java.util.Date reqDobDate = new java.util.Date(basicInformationModel.getDateOfBirth());
					Date dbDobDate = new Date(reqDobDate.getDate());
					basicInfo.setDob(dbDobDate);

					java.util.Date reqMarriageDate = new java.util.Date(basicInformationModel.getDateOfMarriage());
					Date dbMarriageDate = new Date(reqMarriageDate.getDate());
					basicInfo.setDateOfMarriage(dbMarriageDate);

					java.util.Date reqFirstEntryDate = new java.util.Date(
							basicInformationModel.getFirstDateOfEntyInUS());
					Date dbFirstEntryDate = new Date(reqFirstEntryDate.getDate());
					basicInfo.setFirstDateOfEntryInUS(dbFirstEntryDate);

					basicInfo.setTypeOfVisa(basicInformationModel.getTypeOfVisa());
					basicInfo.setCitizenship(basicInformationModel.getCitizenship());

					basicInfo.setTaxFileYear(taxFiledYearEntity);

					taxFiledYearEntity.setBasicInfo(basicInfo);
				}
				ContactDetails contactDetails = taxPayerModel.getContactDetails();
				if (null != contactDetails) {

					contactDetailsEntity.setAddress(contactDetails.getAddress());
					contactDetailsEntity.setCity(contactDetails.getCity());
					contactDetailsEntity.setAptNo(contactDetails.getAptNo());
					contactDetailsEntity.setState(contactDetails.getState());
					contactDetailsEntity.setZip(contactDetails.getZip());
					contactDetailsEntity.setCountry(contactDetails.getCountry());
					contactDetailsEntity.setMobilePhone(contactDetails.getMobilePhone());
					contactDetailsEntity.setAlternatePhone(contactDetails.getAlternateNumber());
					contactDetailsEntity.setIndiaNumber(contactDetails.getIndiaNumber());
					contactDetailsEntity.setEmail(contactDetails.getEmail());
					contactDetailsEntity.setTimezone(contactDetails.getTimezone());

					if (null != contactDetails.getResidencyDetailsforStates()
							&& contactDetails.getResidencyDetailsforStates().size() > 0) {

						List<ResidencyDetailsforStates> residencyDetailsforStatesList = contactDetails
								.getResidencyDetailsforStates();
						// residencyDetailsForStatesEntityList.clear();
						// clear the existing entries
						clearResidencyStatesDetails(residencyDetailsForStatesEntityList, "basic");
						for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
							for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
								ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
								residencyDetailsForStatesEntity.setTaxYear(residencyDetailsforStates.getTaxYear());
								residencyDetailsForStatesEntity.setStatesResided(taxYearInfo.getStateResided());

								java.util.Date reqstartDate = new java.util.Date(taxYearInfo.getStartDate());
								Date dbStartDate = new Date(reqstartDate.getDate());
								residencyDetailsForStatesEntity.setStartDate(dbStartDate);

								java.util.Date reqEndDate = new java.util.Date(taxYearInfo.getEndDate());
								Date dbEndDate = new Date(reqEndDate.getDate());
								residencyDetailsForStatesEntity.setEndDate(dbEndDate);

								residencyDetailsForStatesEntity.setTypeOfResidencyDetails("basic");
								residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);

								residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
							}
						}
						// taxFiledYearEntity.setResidencyDetailsforStatesList(residencyDetailsForStatesEntityList);
					}
					contactDetailsEntity.setTaxFileYear(taxFiledYearEntity);
					taxFiledYearEntity.setContactDetails(contactDetailsEntity);
				}
				if (null != taxPayerModel.getSpouseDetails()) {
					SpouseDetails spouseDetails = taxPayerModel.getSpouseDetails();

					spouseDetailsEntity.setFirstName(spouseDetails.getName().getFirstName());
					spouseDetailsEntity.setMiddleName(spouseDetails.getName().getMiddleName());
					spouseDetailsEntity.setLastName(spouseDetails.getName().getLastName());
					spouseDetailsEntity.setSsnOrItin(spouseDetails.getSsnOrItin());

					java.util.Date reqDobDate = new java.util.Date(spouseDetails.getDateOfBirth());
					Date dbDobDate = new Date(reqDobDate.getDate());
					spouseDetailsEntity.setDateOfBirth(dbDobDate);

					spouseDetailsEntity.setCheckIfITINToBeApplied(spouseDetails.isCheckIfITINToBeApplied());
					spouseDetailsEntity.setCheckIfITINToBeRenewed(spouseDetails.isCheckIfITINToBeRenewed());
					spouseDetailsEntity.setITINRenewed(spouseDetails.isITINRenewed());

					java.util.Date reqFirstEntryDate = new java.util.Date(spouseDetails.getEntryDateIntoUS());
					Date dbFirstEntryDate = new Date(reqFirstEntryDate.getDate());
					spouseDetailsEntity.setEntryDateIntoUS(dbFirstEntryDate);
					spouseDetailsEntity.setOccupation(spouseDetails.getOccupation());
					spouseDetailsEntity.setLivingMoreThan6Months(spouseDetails.isLivingMoreThan6Months());
					spouseDetailsEntity.setDidYourSpouseisWorkedinXX(spouseDetails.isDidYourSpouseisWorkedinXX());

					LOGGER.info("residency size: " + spouseDetails.getResidencyDetailsforStates().size());

					if (null != spouseDetails.getResidencyDetailsforStates()
							&& spouseDetails.getResidencyDetailsforStates().size() > 0) {
						List<ResidencyDetailsforStates> residencyDetailsforStatesList = spouseDetails
								.getResidencyDetailsforStates();
						LOGGER.info("spouse residency list: " + residencyDetailsforStatesList.size());
						// residencyDetailsForStatesEntityList.clear();
						clearResidencyStatesDetails(residencyDetailsForStatesEntityList, "spouse");
						for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
							for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
								ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
								residencyDetailsForStatesEntity.setTaxYear(residencyDetailsforStates.getTaxYear());
								residencyDetailsForStatesEntity.setStatesResided(taxYearInfo.getStateResided());

								java.util.Date reqstartDate = new java.util.Date(taxYearInfo.getStartDate());
								Date dbStartDate = new Date(reqstartDate.getDate());
								residencyDetailsForStatesEntity.setStartDate(dbStartDate);

								java.util.Date reqEndDate = new java.util.Date(taxYearInfo.getEndDate());
								Date dbEndDate = new Date(reqEndDate.getDate());
								residencyDetailsForStatesEntity.setEndDate(dbEndDate);

								residencyDetailsForStatesEntity.setTypeOfResidencyDetails("spouse");
								residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);
								LOGGER.info("adding residency details for spouse");
								residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
							}
						}
					}
					spouseDetailsEntity.setTaxFileYear(taxFiledYearEntity);
					taxFiledYearEntity.setSpouseDetails(spouseDetailsEntity);
				} else {
					LOGGER.info("no spouse details");
				}
				taxFiledYearEntity.setResidencyDetailsforStatesList(residencyDetailsForStatesEntityList);
				userRepository.save(userEntity);
				return "successfully updated info";
			} else {
				return "userId not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "an error has occured";
	}

	@PutMapping("/updateuser/{user_id}/{tax_year}/dependentInfo")
	public Object updateUserDependentInfo(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {
			DependentInformation dependentInformation = taxPayerModel.getDependentInformation();

			if (null != dependentInformation) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();

					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								LOGGER.info("updating existing dependentInformation details");

								DependentInformationEntity dependentInformationEntity = taxFiledYearEntity
										.getDependentInformation();
								if (null == dependentInformationEntity) {
									dependentInformationEntity = new DependentInformationEntity();
									taxFiledYearEntity.setDependentInformation(dependentInformationEntity);
								}

								dependentInformationEntity.setFirstName(dependentInformation.getName().getFirstName());
								dependentInformationEntity
										.setMiddleName(dependentInformation.getName().getMiddleName());
								dependentInformationEntity.setLastName(dependentInformation.getName().getLastName());
								dependentInformationEntity.setSsnitin(dependentInformation.getSsnOrItin());

								java.util.Date reqDobDate = new java.util.Date(dependentInformation.getDateOfBirth());
								Date dbDobDate = new Date(reqDobDate.getDate());
								dependentInformationEntity.setDateOfBirth(dbDobDate);

								dependentInformationEntity
										.setCheckIfITINToBeApplied(dependentInformation.isCheckIfITINToBeApplied());
								dependentInformationEntity
										.setCheckIfITINToBeRenewed(dependentInformation.isCheckIfITINToBeRenewed());
								dependentInformationEntity.setITINRenewed(dependentInformation.isITINRenewed());

								dependentInformationEntity.setRelationship(dependentInformation.getRelationship());
								dependentInformationEntity.setVisaStatus(dependentInformation.getVisaStatus());
								dependentInformationEntity
										.setYouAndSpouseWorking(dependentInformation.isIfYouAndYourSpouseAreWorking());
								dependentInformationEntity
										.setLivedForMoreThan06Months(dependentInformation.isLivingMoreThan6Months());
								dependentInformationEntity.setProvidedMoreThan50PESupport(
										dependentInformation.isIfProvidedMoreThan50PERSupportDuringTheYearXX());

								if (null != dependentInformation.getResidencyDetailsforStates()
										&& dependentInformation.getResidencyDetailsforStates().size() > 0) {
									List<ResidencyDetailsforStates> residencyDetailsforStatesList = dependentInformation
											.getResidencyDetailsforStates();
									LOGGER.info("spouse residency list: " + residencyDetailsforStatesList.size());
									Set<ResidencyDetailsForStatesEntity> residencyStatesSet = taxFiledYearEntity
											.getResidencyDetailsforStatesList();
									// clear the existing entries
									clearResidencyStatesDetails(residencyStatesSet, "dependent");
									// residencyDetailsForStatesEntityList.clear();
									for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
										for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
											ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
											residencyDetailsForStatesEntity
													.setTaxYear(residencyDetailsforStates.getTaxYear());
											residencyDetailsForStatesEntity
													.setStatesResided(taxYearInfo.getStateResided());

											java.util.Date reqstartDate = new java.util.Date(
													taxYearInfo.getStartDate());
											Date dbStartDate = new Date(reqstartDate.getDate());
											residencyDetailsForStatesEntity.setStartDate(dbStartDate);

											java.util.Date reqEndDate = new java.util.Date(taxYearInfo.getEndDate());
											Date dbEndDate = new Date(reqEndDate.getDate());
											residencyDetailsForStatesEntity.setEndDate(dbEndDate);

											residencyDetailsForStatesEntity.setTypeOfResidencyDetails("dependent");
											residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);
											LOGGER.info("adding residency details for spouse");
											// residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
											residencyStatesSet.add(residencyDetailsForStatesEntity);
										}
									}
								}
								userRepository.save(userEntity);

							}
						}
					}

				} else {
					return "user not found";
				}
			} else {
				return "dependent info should not be null/empty";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}

		return "success";
	}

	public void clearResidencyStatesDetails(Set<ResidencyDetailsForStatesEntity> residencyDetailsForStatesEntity,
			String type) {
		switch (type) {
		case "spouse":
		case "basic":
		case "dependent":
			break;
		default:
			LOGGER.info("invalid residency type");
			return;
		}
		Iterator<ResidencyDetailsForStatesEntity> residencyDetailsforStatesItr = residencyDetailsForStatesEntity
				.iterator();
		while (residencyDetailsforStatesItr.hasNext()) {
			ResidencyDetailsForStatesEntity residencyDetailsforStates = residencyDetailsforStatesItr.next();
			if (residencyDetailsforStates.getTypeOfResidencyDetails().equals(type)) {
				residencyDetailsforStatesItr.remove();
			}
		}
	}

	@PutMapping("/updateuser/{user_id}/bankInfo")
	public Object updateUserBankInfo(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */

		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {
			BankDetails bankDetails = taxPayerModel.getBankDetails();

			if (null != bankDetails) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == 2019) {
								LOGGER.info("updating existing bank details");
								BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
								if (null == bankDetailsEntity) {
									bankDetailsEntity = new BankDetailsEntity();
									taxFiledYearEntity.setBankDetails(bankDetailsEntity);
								}
								bankDetailsEntity.setBankAccountNumber(bankDetails.getBankAccountNumber());
								bankDetailsEntity.setBankAccountTpe(bankDetails.getBankAccountType());
								bankDetailsEntity.setBankName(bankDetails.getBankName());
								bankDetailsEntity.setRoutingNumber(bankDetails.getRoutingNumber());
								bankDetailsEntity.setTaxFileYear(taxFiledYearEntity);

								LOGGER.info("bank details updated successfully!");
							}
						}
					} else {
						LOGGER.info("inserting into db as no records found");

						taxFiledYearEntityList = new HashSet<>();
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(2019);

						BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
						bankDetailsEntity.setBankAccountNumber(bankDetails.getBankAccountNumber());
						bankDetailsEntity.setBankAccountTpe(bankDetails.getBankAccountType());
						bankDetailsEntity.setBankName(bankDetails.getBankName());
						bankDetailsEntity.setRoutingNumber(bankDetails.getRoutingNumber());

						bankDetailsEntity.setTaxFileYear(taxFiledYearEntity);
						taxFiledYearEntity.setBankDetails(bankDetailsEntity);
						// taxFiledYearEntity.setUserEntity(userEntity);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userEntity.setTaxFiledYearList(taxFiledYearEntityList);
						userRepository.save(userEntity);
					}

				} else {
					return "user not found";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}

		return "success";
	}

	@PutMapping("/updateuser/{user_id}/{tax_year}/otherIncomeInfo")
	public String manjoOtherInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {

		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {
			OtherIncomeInfoModel otherIncomeInfoModel = taxPayerModel.getOtherIncomeInfoModel();
			if (null != otherIncomeInfoModel && otherIncomeInfoModel.getOtherInfoDataList() != null
					&& otherIncomeInfoModel.getOtherInfoDataList().size() > 0) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntitySet = taxFiledYearEntity
										.getOtherIncomeInformatonEntityList();
								otherIncomeInformatonEntitySet.clear();
								for (OtherIncomeInfoData otherIncomeInfoData : otherIncomeInfoModel
										.getOtherInfoDataList()) {
									OtherIncomeInformatonEntity otherIncomeInformatonEntity = new OtherIncomeInformatonEntity();
									otherIncomeInformatonEntity.setQuestion(otherIncomeInfoData.getQuestion());
									otherIncomeInformatonEntity.setAnswer(otherIncomeInfoData.getAnswer());
									otherIncomeInformatonEntity.setComments(otherIncomeInfoData.getComments());
									otherIncomeInformatonEntitySet.add(otherIncomeInformatonEntity);

									otherIncomeInformatonEntity.setTaxFileYear(taxFiledYearEntity);
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@PutMapping("/updateuser/{user_id}/{tax_year}/additionalInfo")
	public String nikhilOtherInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {
			AdditionalInfoModel additionalInfoModel = taxPayerModel.getAdditionalInfoModel();
			if (null != additionalInfoModel && additionalInfoModel.getAdditionalInfoDataList() != null
					&& additionalInfoModel.getAdditionalInfoDataList().size() > 0) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								Set<AdditionalInformationEntity> additionalInformatonEntitySet = taxFiledYearEntity
										.getAdditionalInformationEntityList();
								additionalInformatonEntitySet.clear();
								for (OtherIncomeInfoData otherIncomeInfoData : additionalInfoModel
										.getAdditionalInfoDataList()) {
									AdditionalInformationEntity additionalInformatonEntity = new AdditionalInformationEntity();
									additionalInformatonEntity.setQuestion(otherIncomeInfoData.getQuestion());
									additionalInformatonEntity.setAnswer(otherIncomeInfoData.getAnswer());
									additionalInformatonEntity.setComments(otherIncomeInfoData.getComments());
									additionalInformatonEntitySet.add(additionalInformatonEntity);

									additionalInformatonEntity.setTaxFileYear(taxFiledYearEntity);
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

}
