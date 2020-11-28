package com.company.taxfiler.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.AdditionalInfoModel;
import com.company.model.BankDetails;
import com.company.model.BasicInformation;
import com.company.model.ContactDetails;
import com.company.model.DayCareModel;
import com.company.model.DependentInformation;
import com.company.model.Fbar;
import com.company.model.Name;
import com.company.model.OtherIncomeInfoData;
import com.company.model.OtherIncomeInfoModel;
import com.company.model.OtherInformation;
import com.company.model.ResidencyDetailsforStates;
import com.company.model.SpouseDetails;
import com.company.model.TaxPayer;
import com.company.model.TaxYearInfo;
import com.company.taxfiler.dao.AdditionalInformationEntity;
import com.company.taxfiler.dao.BankDetailsEntity;
import com.company.taxfiler.dao.BasicInfoEntity;
import com.company.taxfiler.dao.ContactDetailsEntity;
import com.company.taxfiler.dao.DayCareEntity;
import com.company.taxfiler.dao.DependentInformationEntity;
import com.company.taxfiler.dao.FbarEntity;
import com.company.taxfiler.dao.MessagesEntity;
import com.company.taxfiler.dao.OtherIncomeInformatonEntity;
import com.company.taxfiler.dao.OtherInformationEntity;
import com.company.taxfiler.dao.ResidencyDetailsForStatesEntity;
import com.company.taxfiler.dao.SpouseDetailsEntity;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.MessageModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.TaxfilerUtil;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@Transactional
public class UpdateUserController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaxfilerUtil taxfilerUtil;
	@Autowired
	private HttpServletRequest httpServletRequest;
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private final static String DEFAULT_MAIN_STATUS = "SCHEDULING";
	private final static String DEFAULT_SUB_STATUS = "PENDING";

	@PutMapping("/updateuser/{user_id}/{tax_year}/basicInfo")
	public Object updateUserBasicInfo(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
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
					userEntity.setTaxFiledYearList(taxFiledYearEntityList);
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
					LOGGER.info("new tax file year {}", taxYear);
					taxFiledYearEntity = new TaxFiledYearEntity();
					taxFiledYearEntity.setYear(taxYear);
					taxFiledYearEntity.setUserEntity(userEntity);
					taxFiledYearEntityList.add(taxFiledYearEntity);

					basicInfo = new BasicInfoEntity();
					contactDetailsEntity = new ContactDetailsEntity();
					spouseDetailsEntity = new SpouseDetailsEntity();
					residencyDetailsForStatesEntityList = new HashSet<>();
					taxFiledYearEntity.setResidencyDetailsforStatesList(residencyDetailsForStatesEntityList);
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
					LOGGER.info("settin up basic info");
					basicInfo.setMartialStatus(basicInformationModel.getMartialStatus());
					basicInfo.setFirstName(basicInformationModel.getName().getFirstName());
					basicInfo.setMiddleName(basicInformationModel.getName().getMiddleName());
					basicInfo.setLastName(basicInformationModel.getName().getLastName());
					basicInfo.setSsn(basicInformationModel.getSsn());
					basicInfo.setOccupation(basicInformationModel.getOccupation());

					if (StringUtils.isNotBlank(basicInformationModel.getDateOfBirth()))
						basicInfo.setDob(convertStringDateToSqlDate(basicInformationModel.getDateOfBirth()));
					if (StringUtils.isNotBlank(basicInformationModel.getDateOfMarriage()))
						basicInfo.setDateOfMarriage(
								convertStringDateToSqlDate(basicInformationModel.getDateOfMarriage()));
					if (StringUtils.isNotBlank(basicInformationModel.getFirstDateOfEntyInUS()))
						basicInfo.setFirstDateOfEntryInUS(
								convertStringDateToSqlDate(basicInformationModel.getFirstDateOfEntyInUS()));

					basicInfo.setTypeOfVisa(basicInformationModel.getTypeOfVisa());
					basicInfo.setCitizenship(basicInformationModel.getCitizenship());

					basicInfo.setTaxFileYear(taxFiledYearEntity);

					taxFiledYearEntity.setBasicInfo(basicInfo);
				} else {
					LOGGER.info("basic info req obj not available");
				}
				ContactDetails contactDetails = taxPayerModel.getContactDetails();
				if (null != contactDetails) {
					LOGGER.info("settin up contact info");
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

					if (null != contactDetails.getAddressOfLivingInTaxYear()
							&& contactDetails.getAddressOfLivingInTaxYear().size() > 0) {

						Set<ResidencyDetailsforStates> residencyDetailsforStatesList = contactDetails
								.getAddressOfLivingInTaxYear();
						clearResidencyStatesDetails(residencyDetailsForStatesEntityList, "basic");
						for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
							for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
								ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
								residencyDetailsForStatesEntity.setTaxYear(residencyDetailsforStates.getTaxYear());
								residencyDetailsForStatesEntity.setStatesResided(taxYearInfo.getStateResided());

								if (StringUtils.isNotBlank(taxYearInfo.getStartDate()))
									residencyDetailsForStatesEntity
											.setStartDate(convertStringDateToSqlDate(taxYearInfo.getStartDate()));
								if (StringUtils.isNotBlank(taxYearInfo.getEndDate()))
									residencyDetailsForStatesEntity
											.setEndDate(convertStringDateToSqlDate(taxYearInfo.getEndDate()));

								residencyDetailsForStatesEntity.setTypeOfResidencyDetails("basic");
								residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);

								residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
							}
						}
					}
					contactDetailsEntity.setTaxFileYear(taxFiledYearEntity);
					taxFiledYearEntity.setContactDetails(contactDetailsEntity);
				} else {
					LOGGER.info("contact info req obj not available");
				}
				if (null != taxPayerModel.getSpouseDetails()) {
					LOGGER.info("settin up spouse info");
					SpouseDetails spouseDetails = taxPayerModel.getSpouseDetails();

					spouseDetailsEntity.setFirstName(spouseDetails.getName().getFirstName());
					spouseDetailsEntity.setMiddleName(spouseDetails.getName().getMiddleName());
					spouseDetailsEntity.setLastName(spouseDetails.getName().getLastName());
					spouseDetailsEntity.setSsnOrItin(spouseDetails.getSsnOrItin());

					if (StringUtils.isNotBlank(spouseDetails.getDateOfBirth()))
						spouseDetailsEntity.setDateOfBirth(convertStringDateToSqlDate(spouseDetails.getDateOfBirth()));

					spouseDetailsEntity.setCheckIfITINToBeApplied(spouseDetails.isCheckIfITINToBeApplied());
					spouseDetailsEntity.setCheckIfITINToBeRenewed(spouseDetails.isCheckIfITINToBeRenewed());
					spouseDetailsEntity.setITINRenewed(spouseDetails.isITINRenewed());

					if (StringUtils.isNotBlank(spouseDetails.getEntryDateIntoUS()))
						spouseDetailsEntity
								.setEntryDateIntoUS(convertStringDateToSqlDate(spouseDetails.getEntryDateIntoUS()));
					spouseDetailsEntity.setOccupation(spouseDetails.getOccupation());
					spouseDetailsEntity.setLivingMoreThan6Months(spouseDetails.isLivingMoreThan6Months());
					spouseDetailsEntity.setDidYourSpouseisWorkedinXX(spouseDetails.isDidYourSpouseisWorkedinXX());
					if (spouseDetails.isDidYourSpouseisWorkedinXX()) {
						spouseDetailsEntity.setSpouseWorkStatusComments(spouseDetails.getSpouseWorkStatusComments());
					}
					spouseDetailsEntity
							.setDidYouWorkMoreThanOneEmployerInXX(spouseDetails.isDidYouWorkMoreThanOneEmployerInXX());
					if (spouseDetails.isDidYouWorkMoreThanOneEmployerInXX()) {
						spouseDetailsEntity.setMoreThanOneEmployerWorkStatusComments(
								spouseDetails.getMoreThanOneEmployerWorkStatusComments());
					}

					LOGGER.info("residency size: " + spouseDetails.getAddressOfLivingInTaxYear().size());

					if (null != spouseDetails.getAddressOfLivingInTaxYear()
							&& spouseDetails.getAddressOfLivingInTaxYear().size() > 0) {
						Set<ResidencyDetailsforStates> residencyDetailsforStatesList = spouseDetails
								.getAddressOfLivingInTaxYear();
						LOGGER.info("spouse residency list: " + residencyDetailsforStatesList.size());
						clearResidencyStatesDetails(residencyDetailsForStatesEntityList, "spouse");
						for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
							for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
								ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
								residencyDetailsForStatesEntity.setTaxYear(residencyDetailsforStates.getTaxYear());
								residencyDetailsForStatesEntity.setStatesResided(taxYearInfo.getStateResided());

								if (StringUtils.isNotBlank(taxYearInfo.getStartDate()))
									residencyDetailsForStatesEntity
											.setStartDate(convertStringDateToSqlDate(taxYearInfo.getStartDate()));
								if (StringUtils.isNotBlank(taxYearInfo.getEndDate()))
									residencyDetailsForStatesEntity
											.setEndDate(convertStringDateToSqlDate(taxYearInfo.getEndDate()));

								residencyDetailsForStatesEntity.setTypeOfResidencyDetails("spouse");
								residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);
								LOGGER.info("adding residency details for spouse");
								// residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
								taxFiledYearEntity.getResidencyDetailsforStatesList()
										.add(residencyDetailsForStatesEntity);
							}
						}
					}
					spouseDetailsEntity.setTaxFileYear(taxFiledYearEntity);
					taxFiledYearEntity.setSpouseDetails(spouseDetailsEntity);
				} else {
					LOGGER.info("no spouse details");
				}
				// taxFiledYearEntity.setResidencyDetailsforStatesList(residencyDetailsForStatesEntityList);
				// taxFiledYearEntity.getResidencyDetailsforStatesList().
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

	@GetMapping("/{user_id}/{tax_year}/basicInfo")
	public Object getUserBasicInfo(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		TaxPayer taxPayerModel = new TaxPayer();

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();

				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null == taxFiledYearEntityList || taxFiledYearEntityList.size() == 0) {
					return "details not available";
				} else {
					for (TaxFiledYearEntity taxFiledYearEntityTemp : taxFiledYearEntityList) {
						if (taxFiledYearEntityTemp.getYear() == taxYear) {

							/**
							 * get basic info details
							 */
							BasicInfoEntity basicEntity = taxFiledYearEntityTemp.getBasicInfo();
							String basicEntityStr = taxfilerUtil.convertObjectTOString(basicEntity);
							BasicInformation basicInfoModel = (BasicInformation) taxfilerUtil
									.convertStringToObject(basicEntityStr, BasicInformation.class);
							Name name = new Name();
							name.setFirstName(basicEntity.getFirstName());
							name.setMiddleName(basicEntity.getMiddleName());
							name.setLastName(basicEntity.getLastName());
							basicInfoModel.setName(name);
							basicInfoModel.setDateOfBirth(basicEntity.getDob().toString());
							basicInfoModel.setFirstDateOfEntyInUS(basicEntity.getFirstDateOfEntryInUS().toString());
							basicInfoModel.setFilingStatus(basicEntity.getMartialStatus());
							taxPayerModel.setBasicInformation(basicInfoModel);

							/**
							 * get contact details
							 */
							ContactDetailsEntity contactEntity = taxFiledYearEntityTemp.getContactDetails();
							String contactEntityStr = taxfilerUtil.convertObjectTOString(contactEntity);
							ContactDetails contactDetailsModel = (ContactDetails) taxfilerUtil
									.convertStringToObject(contactEntityStr, ContactDetails.class);

							Set<ResidencyDetailsForStatesEntity> residencyStatesEntitySet = taxFiledYearEntityTemp
									.getResidencyDetailsforStatesList();
							Set<ResidencyDetailsforStates> residencyDetailsforStatesSetModel = new HashSet<>();
							Set<TaxYearInfo> taxYearInfoList = new HashSet<>();
							LOGGER.info("residencyStatesEntitySet size {}", residencyStatesEntitySet.size());
							ResidencyDetailsforStates model = new ResidencyDetailsforStates();
							for (ResidencyDetailsForStatesEntity entity : residencyStatesEntitySet) {
								model.setTaxYear((int) entity.getTaxYear());
								TaxYearInfo info = new TaxYearInfo();
								info.setStartDate(entity.getStartDate().toString());
								info.setEndDate(entity.getEndDate().toString());
								info.setStateResided(entity.getStatesResided());
								taxYearInfoList.add(info);
								model.setTaxYearInfoList(taxYearInfoList);
								residencyDetailsforStatesSetModel.add(model);

							}

							// String residencyDetailsStr =
							// taxfilerUtil.convertObjectTOString(taxFiledYearEntityTemp.getResidencyDetailsforStatesList());
							// Set<ResidencyDetailsforStates> residencyDetailsforStatesSetModel =
							// (Set<ResidencyDetailsforStates>)taxfilerUtil.convertStringToObject(residencyDetailsStr,
							// Set.class);
							contactDetailsModel.setAddressOfLivingInTaxYear(residencyDetailsforStatesSetModel);
							taxPayerModel.setContactDetails(contactDetailsModel);

							/**
							 * get spouse details
							 */
							SpouseDetailsEntity spouseDetailsEntity = taxFiledYearEntityTemp.getSpouseDetails();
							String spouseEntityStr = taxfilerUtil.convertObjectTOString(spouseDetailsEntity);
							SpouseDetails spouseDetailsModel = (SpouseDetails) taxfilerUtil
									.convertStringToObject(spouseEntityStr, SpouseDetails.class);
							Name spouseName = new Name();
							spouseName.setFirstName(spouseDetailsEntity.getFirstName());
							spouseName.setMiddleName(spouseDetailsEntity.getMiddleName());
							spouseName.setLastName(spouseDetailsEntity.getLastName());
							spouseDetailsModel.setName(spouseName);
							spouseDetailsModel.setAddressOfLivingInTaxYear(residencyDetailsforStatesSetModel);
							taxPayerModel.setSpouseDetails(spouseDetailsModel);
							return taxPayerModel;
						}
					}
				}
			} else {
				return "userId not found";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "details not available";
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

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			DependentInformation dependentInformation = taxPayerModel.getDependentInformation();

			if (null != dependentInformation) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					boolean isUpdatedOrInserted = false;
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

								if (StringUtils.isNotBlank(dependentInformation.getDateOfBirth()))
									dependentInformationEntity.setDateOfBirth(
											convertStringDateToSqlDate(dependentInformation.getDateOfBirth()));

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
									clearResidencyStatesDetails(residencyStatesSet, "dependent");
									for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
										for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
											ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
											residencyDetailsForStatesEntity
													.setTaxYear(residencyDetailsforStates.getTaxYear());
											residencyDetailsForStatesEntity
													.setStatesResided(taxYearInfo.getStateResided());

											if (StringUtils.isNotBlank(taxYearInfo.getStartDate()))
												residencyDetailsForStatesEntity.setStartDate(
														convertStringDateToSqlDate(taxYearInfo.getStartDate()));
											if (StringUtils.isNotBlank(taxYearInfo.getEndDate()))
												residencyDetailsForStatesEntity.setEndDate(
														convertStringDateToSqlDate(taxYearInfo.getEndDate()));

											residencyDetailsForStatesEntity.setTypeOfResidencyDetails("dependent");
											residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);
											LOGGER.info("adding residency details for spouse");
											residencyStatesSet.add(residencyDetailsForStatesEntity);
										}
									}
								}
								// setting daycare details
								if (null != dependentInformation.getDayCareDetails()) {
									DayCareModel dayCareModel = dependentInformation.getDayCareDetails();
									DayCareEntity dayCareEntity = new DayCareEntity();
									dayCareEntity.setAddress(dayCareModel.getAddress());
									dayCareEntity.setInstName(dayCareModel.getInstName());
									dayCareEntity.setInstTaxId(dayCareModel.getInstTaxId());
									dayCareEntity.setDoorNo(dayCareModel.getDoorNo());
									dayCareEntity.setCity(dayCareModel.getCity());
									dayCareEntity.setState(dayCareModel.getState());
									dayCareEntity.setZip(dayCareModel.getZip());
									
									dayCareEntity.setDependentInfo(dependentInformationEntity);
									dependentInformationEntity.setDayCareEntity(dayCareEntity);
								}

								dependentInformationEntity.setTaxFileYear(taxFiledYearEntity);
								userRepository.save(userEntity);
								isUpdatedOrInserted = true;
								break;
							}
						}
					}
					if (!isUpdatedOrInserted) {
						LOGGER.info("inserting dependent information");
						// insert data
						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);
						DependentInformationEntity dependentInformationEntity = new DependentInformationEntity();
						dependentInformationEntity.setFirstName(dependentInformation.getName().getFirstName());
						dependentInformationEntity.setMiddleName(dependentInformation.getName().getMiddleName());
						dependentInformationEntity.setLastName(dependentInformation.getName().getLastName());
						dependentInformationEntity.setSsnitin(dependentInformation.getSsnOrItin());

						if (StringUtils.isNotBlank(dependentInformation.getDateOfBirth()))
							dependentInformationEntity
									.setDateOfBirth(convertStringDateToSqlDate(dependentInformation.getDateOfBirth()));

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
						taxFiledYearEntity.setDependentInformation(dependentInformationEntity);

						if (null != dependentInformation.getResidencyDetailsforStates()
								&& dependentInformation.getResidencyDetailsforStates().size() > 0) {
							List<ResidencyDetailsforStates> residencyDetailsforStatesList = dependentInformation
									.getResidencyDetailsforStates();
							LOGGER.info("spouse residency list: " + residencyDetailsforStatesList.size());
							Set<ResidencyDetailsForStatesEntity> residencyStatesSet = new HashSet<>();
							// clearResidencyStatesDetails(residencyStatesSet, "dependent");
							for (ResidencyDetailsforStates residencyDetailsforStates : residencyDetailsforStatesList) {
								for (TaxYearInfo taxYearInfo : residencyDetailsforStates.getTaxYearInfoList()) {
									ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity = new ResidencyDetailsForStatesEntity();
									residencyDetailsForStatesEntity.setTaxYear(residencyDetailsforStates.getTaxYear());
									residencyDetailsForStatesEntity.setStatesResided(taxYearInfo.getStateResided());

									if (StringUtils.isNotBlank(taxYearInfo.getStartDate()))
										residencyDetailsForStatesEntity
												.setStartDate(convertStringDateToSqlDate(taxYearInfo.getStartDate()));
									if (StringUtils.isNotBlank(taxYearInfo.getEndDate()))
										residencyDetailsForStatesEntity
												.setEndDate(convertStringDateToSqlDate(taxYearInfo.getEndDate()));

									residencyDetailsForStatesEntity.setTypeOfResidencyDetails("dependent");
									residencyDetailsForStatesEntity.setTaxFileYear(taxFiledYearEntity);
									LOGGER.info("adding residency details for spouse");
									residencyStatesSet.add(residencyDetailsForStatesEntity);
								}
							}
							taxFiledYearEntity.setResidencyDetailsforStatesList(residencyStatesSet);
						}
						dependentInformationEntity.setTaxFileYear(taxFiledYearEntity);
						userRepository.save(userEntity);
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

	@GetMapping("/updateuser/{user_id}/{tax_year}/dependentInfo")
	public Object getUserDependentInfo(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		DependentInformation dependentInformation = new DependentInformation();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							LOGGER.info("getting existing dependentInformation details");

							DependentInformationEntity dependentInformationEntity = taxFiledYearEntity
									.getDependentInformation();
							if (null == dependentInformationEntity) {
								return "no dependent info";
							}

							Name name = new Name();
							name.setFirstName(dependentInformationEntity.getFirstName());
							name.setMiddleName(dependentInformationEntity.getMiddleName());
							name.setLastName(dependentInformationEntity.getLastName());
							dependentInformation.setName(name);
							dependentInformation.setSsnOrItin(dependentInformationEntity.getSsnitin());

							dependentInformation.setDateOfBirth(dependentInformationEntity.getDateOfBirth().toString());

							dependentInformation
									.setCheckIfITINToBeApplied(dependentInformationEntity.isCheckIfITINToBeApplied());
							dependentInformation
									.setCheckIfITINToBeRenewed(dependentInformationEntity.isCheckIfITINToBeRenewed());
							dependentInformation.setITINRenewed(dependentInformationEntity.isITINRenewed());

							dependentInformation.setRelationship(dependentInformationEntity.getRelationship());
							dependentInformation.setVisaStatus(dependentInformationEntity.getVisaStatus());
							dependentInformation.setIfYouAndYourSpouseAreWorking(
									dependentInformationEntity.isYouAndSpouseWorking());
							dependentInformation
									.setLivingMoreThan6Months(dependentInformationEntity.isLivedForMoreThan06Months());
							dependentInformation.setIfProvidedMoreThan50PERSupportDuringTheYearXX(
									dependentInformation.isIfProvidedMoreThan50PERSupportDuringTheYearXX());

							Set<ResidencyDetailsForStatesEntity> residencyDetailsForStatesEntitySet = taxFiledYearEntity
									.getResidencyDetailsforStatesList();

							if (null != residencyDetailsForStatesEntitySet
									&& residencyDetailsForStatesEntitySet.size() > 0) {
								List<ResidencyDetailsforStates> residencyDetailsforStatesList = new ArrayList<>();
								Set<TaxYearInfo> taxYearInfoList = new HashSet<>();
								ResidencyDetailsforStates residencyDetailsforStates = new ResidencyDetailsforStates();
								for (ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity : residencyDetailsForStatesEntitySet) {
									if (residencyDetailsForStatesEntity.getTypeOfResidencyDetails()
											.equals("dependent")) {
										residencyDetailsforStates.setTaxYear(taxYear);

										TaxYearInfo taxYearInfo = new TaxYearInfo();
										taxYearInfo.setEndDate(residencyDetailsForStatesEntity.getEndDate().toString());
										taxYearInfo.setStartDate(
												residencyDetailsForStatesEntity.getStartDate().toString());
										taxYearInfo.setStateResided(residencyDetailsForStatesEntity.getStatesResided());
										taxYearInfoList.add(taxYearInfo);

										residencyDetailsforStates.setTaxYearInfoList(taxYearInfoList);
									}
								}
								residencyDetailsforStatesList.add(residencyDetailsforStates);
								dependentInformation.setResidencyDetailsforStates(residencyDetailsforStatesList);
							}
							//setting daycare details
							if(null != dependentInformationEntity.getDayCareEntity()) {
								DayCareEntity dayCareEntity = dependentInformationEntity.getDayCareEntity();
								DayCareModel dayCareModel = new DayCareModel();
								dayCareModel.setInstName(dayCareEntity.getInstName());
								dayCareModel.setInstTaxId(dayCareEntity.getInstTaxId());
								dayCareModel.setAddress(dayCareEntity.getAddress());
								dayCareModel.setCity(dayCareEntity.getCity());
								dayCareModel.setDoorNo(dayCareEntity.getDoorNo());
								dayCareModel.setState(dayCareEntity.getState());
								dayCareModel.setZip(dayCareEntity.getZip());
								
								dependentInformation.setDayCareDetails(dayCareModel);
							}
							return dependentInformation;
						}
					}
				}

			} else {
				return "user not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}

		return "";
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

	@PutMapping("/updateuser/{user_id}/{tax_year}/bankInfo")
	public Object updateUserBankInfo(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */

		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			BankDetails bankDetails = taxPayerModel.getBankDetails();

			if (null != bankDetails) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					boolean isUpdatedOrInserted = false;
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								LOGGER.info("updating existing bank details");
								BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
								if (null == bankDetailsEntity) {
									bankDetailsEntity = new BankDetailsEntity();
									taxFiledYearEntity.setBankDetails(bankDetailsEntity);
								}
								bankDetailsEntity.setBankAccountNumber(bankDetails.getUSBankAccountNumber());
								bankDetailsEntity.setBankAccountTpe(bankDetails.getTypeOfAccount());
								bankDetailsEntity.setBankName(bankDetails.getNameOfTheBank());
								bankDetailsEntity.setRoutingNumber(bankDetails.getBankRoutingNumber());
								bankDetailsEntity.setNameOfTheAccount(bankDetails.getNameOfTheAccount());
								bankDetailsEntity.setTaxFileYear(taxFiledYearEntity);

								LOGGER.info("bank details updated successfully!");
								isUpdatedOrInserted = true;
							}
						}
					}
					/* else */if (!isUpdatedOrInserted) {
						LOGGER.info("inserting into db as no records found");

						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);

						BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
						bankDetailsEntity.setBankAccountNumber(bankDetails.getUSBankAccountNumber());
						bankDetailsEntity.setBankAccountTpe(bankDetails.getTypeOfAccount());
						bankDetailsEntity.setBankName(bankDetails.getNameOfTheBank());
						bankDetailsEntity.setRoutingNumber(bankDetails.getBankRoutingNumber());
						bankDetailsEntity.setNameOfTheAccount(bankDetails.getNameOfTheAccount());

						taxFiledYearEntity.setBankDetails(bankDetailsEntity);
						bankDetailsEntity.setTaxFileYear(taxFiledYearEntity);
						taxFiledYearEntity.setUserEntity(userEntity);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userEntity.getTaxFiledYearList().addAll(taxFiledYearEntityList);
						// userEntity.setTaxFiledYearList(taxFiledYearEntityList);
						userRepository.save(userEntity);
						isUpdatedOrInserted = true;
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

	@GetMapping("/updateuser/{user_id}/{tax_year}/bankInfo")
	public Object getUserBankInfo(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		BankDetails bankDetails = new BankDetails();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			if (null != bankDetails) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								LOGGER.info("updating existing bank details");
								BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
								if (null == bankDetailsEntity) {
									return "";
								}
								bankDetails.setUSBankAccountNumber(bankDetailsEntity.getBankAccountNumber());
								bankDetails.setTypeOfAccount(bankDetailsEntity.getBankAccountTpe());
								bankDetails.setNameOfTheBank(bankDetailsEntity.getBankName());
								bankDetails.setBankRoutingNumber(bankDetailsEntity.getRoutingNumber());
								bankDetails.setNameOfTheAccount(bankDetailsEntity.getNameOfTheAccount());
								return bankDetails;
							}
						}
					} else {
						return "";
					}

				} else {
					return "user not found";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}

		return "";
	}

	@PutMapping("/updateuser/{user_id}/{tax_year}/otherIncomeInfo")
	public Object otherIncomeInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {

		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			OtherIncomeInfoModel otherIncomeInfoModel = taxPayerModel.getOtherIncomeInfoModel();
			if (null != otherIncomeInfoModel && otherIncomeInfoModel.getOtherInfoDataList() != null
					&& otherIncomeInfoModel.getOtherInfoDataList().size() > 0) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					boolean isUpdatedOrInserted = false;
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
									isUpdatedOrInserted = true;
								}
								break;
							}
						}
					}
					if (!isUpdatedOrInserted) {
						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);
						Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntitySet = new HashSet<>();
						for (OtherIncomeInfoData otherIncomeInfoData : otherIncomeInfoModel.getOtherInfoDataList()) {
							OtherIncomeInformatonEntity otherIncomeInformatonEntity = new OtherIncomeInformatonEntity();
							otherIncomeInformatonEntity.setQuestion(otherIncomeInfoData.getQuestion());
							otherIncomeInformatonEntity.setAnswer(otherIncomeInfoData.getAnswer());
							otherIncomeInformatonEntity.setComments(otherIncomeInfoData.getComments());
							otherIncomeInformatonEntitySet.add(otherIncomeInformatonEntity);

							otherIncomeInformatonEntity.setTaxFileYear(taxFiledYearEntity);
						}
						taxFiledYearEntity.setOtherIncomeInformatonEntityList(otherIncomeInformatonEntitySet);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userEntity.setTaxFiledYearList(taxFiledYearEntityList);
						userRepository.save(userEntity);
						isUpdatedOrInserted = true;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping("/updateuser/{user_id}/{tax_year}/otherIncomeInfo")
	public Object getOtherIncomeInformation(@PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		OtherIncomeInfoModel otherIncomeInfoModel = new OtherIncomeInfoModel();
		Set<OtherIncomeInfoData> otherIncomeInfoDataList = new HashSet<>();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntitySet = taxFiledYearEntity
									.getOtherIncomeInformatonEntityList();
							for (OtherIncomeInformatonEntity otherIncomeInformatonEntity : otherIncomeInformatonEntitySet) {
								OtherIncomeInfoData otherIncomeInfoData = new OtherIncomeInfoData();
								otherIncomeInfoData.setQuestion(otherIncomeInformatonEntity.getQuestion());
								otherIncomeInfoData.setAnswer(otherIncomeInformatonEntity.getAnswer());
								otherIncomeInfoData.setComments(otherIncomeInformatonEntity.getComments());
								otherIncomeInfoDataList.add(otherIncomeInfoData);
							}
							otherIncomeInfoModel.setOtherInfoDataList(otherIncomeInfoDataList);
							return otherIncomeInfoModel;
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
	public Object additionalInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			AdditionalInfoModel additionalInfoModel = taxPayerModel.getAdditionalInfoModel();
			if (null != additionalInfoModel && additionalInfoModel.getAdditionalInfoDataList() != null
					&& additionalInfoModel.getAdditionalInfoDataList().size() > 0) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					boolean isUpdatedOrInserted = false;
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
									isUpdatedOrInserted = true;
								}
								break;
							}
						}
					}
					if (!isUpdatedOrInserted) {
						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);
						Set<AdditionalInformationEntity> additionalInformatonEntitySet = new HashSet<>();
						for (OtherIncomeInfoData otherIncomeInfoData : additionalInfoModel
								.getAdditionalInfoDataList()) {
							AdditionalInformationEntity additionalInformatonEntity = new AdditionalInformationEntity();
							additionalInformatonEntity.setQuestion(otherIncomeInfoData.getQuestion());
							additionalInformatonEntity.setAnswer(otherIncomeInfoData.getAnswer());
							additionalInformatonEntity.setComments(otherIncomeInfoData.getComments());
							additionalInformatonEntitySet.add(additionalInformatonEntity);

							additionalInformatonEntity.setTaxFileYear(taxFiledYearEntity);
						}
						taxFiledYearEntity.setAdditionalInformationEntityList(additionalInformatonEntitySet);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userEntity.setTaxFiledYearList(taxFiledYearEntityList);
						userRepository.save(userEntity);
						isUpdatedOrInserted = true;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping("/updateuser/{user_id}/{tax_year}/additionalInfo")
	public Object getAdditionalInformation(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		AdditionalInfoModel additionalInfoModel = new AdditionalInfoModel();
		Set<OtherIncomeInfoData> additionalInfoDataList = new HashSet<>();
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<AdditionalInformationEntity> additionalInformatonEntitySet = taxFiledYearEntity
									.getAdditionalInformationEntityList();
							for (AdditionalInformationEntity additionalInformationEntity : additionalInformatonEntitySet) {
								OtherIncomeInfoData otherIncomeInfoData = new OtherIncomeInfoData();
								otherIncomeInfoData.setQuestion(additionalInformationEntity.getQuestion());
								otherIncomeInfoData.setAnswer(additionalInformationEntity.getAnswer());
								otherIncomeInfoData.setComments(additionalInformationEntity.getComments());
								additionalInfoDataList.add(otherIncomeInfoData);
							}
							additionalInfoModel.setAdditionalInfoDataList(additionalInfoDataList);
							return additionalInfoModel;
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

	@PutMapping("/updateuser/{user_id}/{tax_year}/otherInfo")
	public Object otherInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			OtherInformation otherInfo = taxPayerModel.getOtherInformation();
			if (null != otherInfo) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					boolean isUpdatedOrInserted = false;
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								OtherInformationEntity otherInformationEntity = taxFiledYearEntity
										.getOtherInformationEntity();
								if (null == otherInformationEntity) {
									otherInformationEntity = new OtherInformationEntity();
									taxFiledYearEntity.setOtherInformationEntity(otherInformationEntity);
								}
								otherInformationEntity.setOtherInformation(otherInfo.getOtherinformation());
								otherInformationEntity.setTaxFileYear(taxFiledYearEntity);
								isUpdatedOrInserted = true;
								break;
							}
						}
					}
					if (!isUpdatedOrInserted) {
						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);
						OtherInformationEntity otherInformationEntity = new OtherInformationEntity();
						otherInformationEntity.setOtherInformation(otherInfo.getOtherinformation());
						otherInformationEntity.setTaxFileYear(taxFiledYearEntity);
						taxFiledYearEntity.setOtherInformationEntity(otherInformationEntity);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userEntity.setTaxFiledYearList(taxFiledYearEntityList);
						userRepository.save(userEntity);
						isUpdatedOrInserted = true;
					}
				}

			} else {
				return "body cannot be null or empty";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping("/updateuser/{user_id}/{tax_year}/otherInfo")
	public Object getOtherInformation(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			OtherInformation otherInfo = new OtherInformation();
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							OtherInformationEntity otherInformationEntity = taxFiledYearEntity
									.getOtherInformationEntity();
							if (null == otherInformationEntity) {
								break;
							} else {
								otherInfo.setOtherinformation(otherInformationEntity.getOtherInformation());
								return otherInfo;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "no content";
	}

	@PutMapping("/updateuser/{user_id}/{tax_year}/fbar")
	public Object fbar(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			Fbar fbar = taxPayerModel.getFbar();
			if (null != fbar) {
				Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
				if (optionalUserEntity.isPresent()) {
					UserEntity userEntity = optionalUserEntity.get();
					Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
					boolean isUpdatedOrInserted = false;
					if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
						for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
							if (taxFiledYearEntity.getYear() == taxYear) {
								FbarEntity fbarEntity = taxFiledYearEntity.getFbarEntity();
								if (null == fbarEntity) {
									fbarEntity = new FbarEntity();
									taxFiledYearEntity.setFbarEntity(fbarEntity);
								}
								fbarEntity.setAccBelongsTo(fbar.getAccBelongsTo());
								fbarEntity.setAccNo(fbar.getAccNo());
								fbarEntity.setBankAddress(fbar.getBankAddress());
								fbarEntity.setBankName(fbar.getNameOfTheBank());
								fbarEntity.setCity(fbar.getCity());
								fbarEntity.setState(fbar.getState());
								fbarEntity.setMaximumValueInTheAcINR(fbar.getMaximumValueInTheAcINR());
								fbarEntity.setPinCode(fbar.getPincode());
								fbarEntity.setTransferToForeignAccount(fbar.getTransferToForeignAccount());
								fbarEntity.setTypeOfAccount(fbar.getTypeOfAccount());
								fbarEntity.setOwnership(fbar.getOwnership());
								fbarEntity.setStreetAddress(fbar.getStreetAddress());
								fbarEntity.setAccountMaintenanceCurrency(fbar.getAccountMaintenanceCurrency());
								fbarEntity.setJointOwnerName(fbar.getJointOwnerName());
								fbarEntity.setAnyIncomeEarnedInXX(fbar.isAnyIncomeEarnedInXX());
								fbarEntity.setIncomeEarnedInXXDetails(fbar.getIncomeEarnedInXXDetails());
								fbarEntity.setMaxValue(fbar.getMaxValue());
								fbarEntity.setValueOfAccount(fbar.getValueOfAccount());

								fbarEntity.setTaxFileYear(taxFiledYearEntity);
								isUpdatedOrInserted = true;
								break;
							}
						}
					}
					if (!isUpdatedOrInserted) {

						if (null == taxFiledYearEntityList) {
							taxFiledYearEntityList = new HashSet<>();
						}
						TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
						taxFiledYearEntity.setYear(taxYear);
						FbarEntity fbarEntity = new FbarEntity();
						fbarEntity.setAccBelongsTo(fbar.getAccBelongsTo());
						fbarEntity.setAccNo(fbar.getAccNo());
						fbarEntity.setBankAddress(fbar.getBankAddress());
						fbarEntity.setBankName(fbar.getNameOfTheBank());
						fbarEntity.setCity(fbar.getCity());
						fbarEntity.setState(fbar.getState());
						fbarEntity.setMaximumValueInTheAcINR(fbar.getMaximumValueInTheAcINR());
						fbarEntity.setPinCode(fbar.getPincode());
						fbarEntity.setTransferToForeignAccount(fbar.getTransferToForeignAccount());
						fbarEntity.setTypeOfAccount(fbar.getTypeOfAccount());
						fbarEntity.setOwnership(fbar.getOwnership());
						fbarEntity.setStreetAddress(fbar.getStreetAddress());
						fbarEntity.setAccountMaintenanceCurrency(fbar.getAccountMaintenanceCurrency());
						fbarEntity.setJointOwnerName(fbar.getJointOwnerName());
						fbarEntity.setAnyIncomeEarnedInXX(fbar.isAnyIncomeEarnedInXX());
						fbarEntity.setIncomeEarnedInXXDetails(fbar.getIncomeEarnedInXXDetails());
						fbarEntity.setMaxValue(fbar.getMaxValue());
						fbarEntity.setValueOfAccount(fbar.getValueOfAccount());
						taxFiledYearEntity.setFbarEntity(fbarEntity);
						fbarEntity.setTaxFileYear(taxFiledYearEntity);
						taxFiledYearEntityList.add(taxFiledYearEntity);
						userRepository.save(userEntity);
						isUpdatedOrInserted = true;
					}
				}

			} else {
				return "request body cannot be null or empty";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping("/updateuser/{user_id}/{tax_year}/fbar")
	public Object getFbar(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Fbar fbar = new Fbar();
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							FbarEntity fbarEntity = taxFiledYearEntity.getFbarEntity();
							if (null == fbarEntity) {
								break;
							} else {
								fbar.setAccBelongsTo(fbarEntity.getAccBelongsTo());
								fbar.setAccNo(fbarEntity.getAccNo());
								fbar.setBankAddress(fbarEntity.getBankAddress());
								fbar.setNameOfTheBank(fbarEntity.getBankName());
								fbar.setCity(fbarEntity.getCity());
								fbar.setState(fbarEntity.getState());
								fbar.setMaximumValueInTheAcINR(fbarEntity.getMaximumValueInTheAcINR());
								fbar.setPincode(fbarEntity.getPinCode());
								fbar.setTransferToForeignAccount(fbarEntity.getTransferToForeignAccount());
								fbar.setTypeOfAccount(fbarEntity.getTypeOfAccount());
								fbar.setOwnership(fbarEntity.getOwnership());
								fbar.setStreetAddress(fbarEntity.getStreetAddress());
								fbar.setAccountMaintenanceCurrency(fbarEntity.getAccountMaintenanceCurrency());
								fbar.setJointOwnerName(fbarEntity.getJointOwnerName());
								fbar.setAnyIncomeEarnedInXX(fbarEntity.isAnyIncomeEarnedInXX());
								fbar.setIncomeEarnedInXXDetails(fbarEntity.getIncomeEarnedInXXDetails());
								fbar.setMaxValue(fbarEntity.getMaxValue());
								fbar.setValueOfAccount(fbarEntity.getValueOfAccount());
								return fbar;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "no content";
	}

	@PostMapping(value = "/updateuser/{user_id}/{tax_year}/message", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object postMessage(@RequestBody MessageModel messageModel, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				boolean isUpdatedOrInserted = false;
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null == messagesEntitySet) {
								messagesEntitySet = new HashSet<MessagesEntity>();
								taxFiledYearEntity.setMessagesEntityList(messagesEntitySet);
							}
							MessagesEntity messagesEntity = new MessagesEntity();
							messagesEntity.setDate(new Date(System.currentTimeMillis()));
							messagesEntity.setSubject(messageModel.getSubject());
							messagesEntity.setMessage(messageModel.getMessage());
							messagesEntity.setSentMessage(messageModel.isSentMessage());
							messagesEntity.setReceivedMessage(messageModel.isReceivedMessage());
							messagesEntity.setMainStatus(DEFAULT_MAIN_STATUS);
							messagesEntity.setSubStatus(DEFAULT_SUB_STATUS);
							messagesEntity.setTaxFileYear(taxFiledYearEntity);
							messagesEntitySet.add(messagesEntity);
							isUpdatedOrInserted = true;
						}
					}
				}
				if (!isUpdatedOrInserted) {
					if (null == taxFiledYearEntityList) {
						taxFiledYearEntityList = new HashSet<>();
					}
					TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
					taxFiledYearEntity.setYear(taxYear);
					Set<MessagesEntity> messagesEntitySet = new HashSet<MessagesEntity>();
					MessagesEntity messagesEntity = new MessagesEntity();
					messagesEntity.setDate(new Date(System.currentTimeMillis()));
					messagesEntity.setSubject(messageModel.getSubject());
					messagesEntity.setMessage(messageModel.getMessage());
					messagesEntity.setSentMessage(messageModel.isSentMessage());
					messagesEntity.setReceivedMessage(messageModel.isReceivedMessage());
					messagesEntity.setMainStatus(DEFAULT_MAIN_STATUS);
					messagesEntity.setSubStatus(DEFAULT_SUB_STATUS);
					messagesEntity.setTaxFileYear(taxFiledYearEntity);
					messagesEntitySet.add(messagesEntity);
					taxFiledYearEntity.setMessagesEntityList(messagesEntitySet);
					taxFiledYearEntityList.add(taxFiledYearEntity);
					userRepository.save(userEntity);
					isUpdatedOrInserted = true;
				}
			} else {
				return "invalid userid";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping(value = "/updateuser/{user_id}/{tax_year}/message")
	public Object getAllMessages(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null == messagesEntitySet) {
								return "no messages";
							} else {
								Set<MessagesEntity> messagesEntitySetResponse = new HashSet<>();
								for (MessagesEntity entity : messagesEntitySet) {
									messagesEntitySetResponse.add(entity);
								}
								return messagesEntitySetResponse;
							}

						}
					}
				}
			} else {
				return "invalid userid";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping(value = "/updateuser/{user_id}/{tax_year}/receivedMessages")
	public Object getReceivedMessages(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null == messagesEntitySet) {
								return "no messages";
							} else {
								Set<MessagesEntity> messagesEntitySetResponse = new HashSet<>();
								for (MessagesEntity entity : messagesEntitySet) {
									if (entity.isReceivedMessage()) {
										messagesEntitySetResponse.add(entity);
									}
								}
								return messagesEntitySetResponse;
							}

						}
					}
				}
			} else {
				return "invalid userid";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	@GetMapping(value = "/updateuser/{user_id}/{tax_year}/sentMessages")
	public Object getSentMessages(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null == messagesEntitySet) {
								return "no messages";
							} else {
								Set<MessagesEntity> messagesEntitySetResponse = new HashSet<>();
								for (MessagesEntity entity : messagesEntitySet) {
									if (entity.isSentMessage()) {
										messagesEntitySetResponse.add(entity);
									}
								}
								return messagesEntitySetResponse;
							}

						}
					}
				}
			} else {
				return "invalid userid";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
		return "success";
	}

	/*
	 * @GetMapping("/updateuser/{user_id}/{tax_year}/basicInfo") public Object
	 * getUserBasicInfo(@PathVariable("user_id") int
	 * userId, @PathVariable("tax_year") int taxYear) { BasicInformation
	 * basicInformation = new BasicInformation(); Object verifySessionIdResponse =
	 * taxfilerUtil.verifySessionId(httpServletRequest); if (verifySessionIdResponse
	 * instanceof String) return verifySessionIdResponse; try {
	 * 
	 * 
	 * Optional<UserEntity> optionalUserEntity = userRepository.findById(userId); if
	 * (optionalUserEntity.isPresent()) { UserEntity userEntity =
	 * optionalUserEntity.get(); Set<TaxFiledYearEntity> taxFiledYearEntityList =
	 * userEntity.getTaxFiledYearList(); boolean taxYearStatus=false;
	 * 
	 * if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
	 * for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) { if
	 * (taxFiledYearEntity.getYear() == taxYear) {
	 * LOGGER.info("get the Basic info details"); BasicInfoEntity basicInfoEntity =
	 * taxFiledYearEntity.getBasicInfo(); taxYearStatus=true;
	 * 
	 * if (null != basicInfoEntity) {
	 * 
	 * LOGGER.info("getting existing basicInfoEntity details");
	 * basicInformation.setCitizenship(basicInfoEntity.getCitizenship());
	 * basicInformation.setDateOfBirth(convertDateToString(basicInfoEntity.getDob())
	 * ); basicInformation.setDateOfMarriage(convertDateToString(basicInfoEntity.
	 * getDateOfMarriage()));
	 * basicInformation.setFirstDateOfEntyInUS(convertDateToString(basicInfoEntity.
	 * getFirstDateOfEntryInUS()));
	 * basicInformation.setMartialStatus(basicInfoEntity.getMartialStatus()); Name
	 * name=new Name(); name.setFirstName(basicInfoEntity.getFirstName());
	 * name.setLastName(basicInfoEntity.getLastName());
	 * name.setMiddleName(basicInfoEntity.getMiddleName());
	 * basicInformation.setName(name);
	 * basicInformation.setOccupation(basicInfoEntity.getOccupation());
	 * basicInformation.setSsn(basicInfoEntity.getSsn());
	 * basicInformation.setTypeOfVisa(basicInfoEntity.getTypeOfVisa());
	 * 
	 * return basicInformation; }else { return "No Basic Info found"; } }
	 * 
	 * } if(!taxYearStatus) return "Tax is not filed for this year";
	 * 
	 * }
	 * 
	 * 
	 * 
	 * } else { return "user not found"; }
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return "an error has occured"; }
	 * 
	 * return ""; }
	 */
	public java.sql.Date convertStringDateToSqlDate(String date) throws ParseException {
		java.util.Date parsed = (java.util.Date) format.parse(date);
		return new java.sql.Date(parsed.getTime());
	}

	public String convertDateToString(Date date) {
		String strDate = format.format(date);

		return strDate;
	}
}
