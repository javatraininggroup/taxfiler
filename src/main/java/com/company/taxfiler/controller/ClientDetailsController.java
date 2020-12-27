package com.company.taxfiler.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.AdditionalInfoModel;
import com.company.model.BankDetails;
import com.company.model.BasicInformation;
import com.company.model.ClientDetails;
import com.company.model.ClientModel;
import com.company.model.ContactDetails;
import com.company.model.DayCareModel;
import com.company.model.DependentInformation;
import com.company.model.DownloadModel;
import com.company.model.ExpensesAndConntributionResponseModel;
import com.company.model.Fbar;
import com.company.model.Name;
import com.company.model.OtherIncomeInfoData;
import com.company.model.OtherIncomeInfoModel;
import com.company.model.OtherInformation;
import com.company.model.RentalIncomeModel;
import com.company.model.ResidencyDetailsforStates;
import com.company.model.SpouseDetails;
import com.company.model.TaxYearInfo;
import com.company.taxfiler.dao.AdditionalInformationEntity;
import com.company.taxfiler.dao.BankDetailsEntity;
import com.company.taxfiler.dao.BasicInfoEntity;
import com.company.taxfiler.dao.CommentsEntity;
import com.company.taxfiler.dao.ContactDetailsEntity;
import com.company.taxfiler.dao.DayCareEntity;
import com.company.taxfiler.dao.DependentInformationEntity;
import com.company.taxfiler.dao.FbarEntity;
import com.company.taxfiler.dao.MessagesEntity;
import com.company.taxfiler.dao.OtherIncomeInformatonEntity;
import com.company.taxfiler.dao.OtherInformationEntity;
import com.company.taxfiler.dao.RentalIncomeEntity;
import com.company.taxfiler.dao.ResidencyDetailsForStatesEntity;
import com.company.taxfiler.dao.SpouseDetailsEntity;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UploadFilesEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.ResponseModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.Constants;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;

@RestController
@RequestMapping(Constants.API)
public class ClientDetailsController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaxfilerUtil taxfilerUtil;

	@Autowired
	private HttpServletRequest httpServletRequest;

	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@GetMapping(Constants.GET_CLIENT_DETAILS_ENDPOINT)
	public Object clientDetails(@PathVariable(Constants.TAX_YEAR) int year, @PathVariable(Constants.USER_ID) int userId)
			throws IOException {
		LOGGER.info("Entering into Client details");

		Set<DependentInformation> dependentInformationList = new HashSet<>();
		ClientModel clientModel = new ClientModel();
		OtherIncomeInfoModel otherIncomeInfoModel = new OtherIncomeInfoModel();
		Set<OtherIncomeInfoData> otherIncomeInfoDataList = new HashSet<>();
		AdditionalInfoModel additionalInfoModel = new AdditionalInfoModel();
		OtherInformation otherInfo = new OtherInformation();
		Fbar fbar = new Fbar();
		BankDetails bankDetails = new BankDetails();
		ClientDetails clientDetails = new ClientDetails();
		BasicInformation basicInformation = new BasicInformation();
		ContactDetails contactDetails = new ContactDetails();
		SpouseDetails spouseDetails = new SpouseDetails();
		RentalIncomeEntity rentalIncomeModel = new RentalIncomeEntity();
		Set<OtherIncomeInfoData> additionalInfoDataList = new HashSet<>();
		List<CommentsEntity> commentsList = new ArrayList<>();

		/**
		 * 1. validate sessionId 2. Get the all person details through the BUSINESS
		 * LOGIC 3.Get details related to file/messageId 4. Else show the error message
		 */

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();

				/*************** START client details *************/
				if (null != userEntity) {

					LOGGER.info("getting existing userEntity details");
					clientDetails.setName(userEntity.getName());
					clientDetails.setAltPhone(userEntity.getAlternatePhone());
					clientDetails.setEmail(userEntity.getEmail());
					clientDetails.setRegPhone(userEntity.getPhone());
					// clientDetails.setStatus(userEntity.get);
					clientDetails.setFileId(Integer.toString(userEntity.getId()));
					clientDetails.setId(userEntity.getId());

				}
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == year) {

							/**************** ResidencyDetailsForStatesEntity *****************/
							Set<ResidencyDetailsForStatesEntity> residencyDetailsForStatesEntitySet = taxFiledYearEntity
									.getResidencyDetailsforStatesList();

							/*************** START MessagesEntity *************/
							/*List<String> allComments = null;
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null != messagesEntitySet) {
								allComments = new ArrayList<String>();
								LOGGER.info("getting existing MessagesEntity details");
								for (MessagesEntity entity : messagesEntitySet) {

									allComments.add(entity.getMessage());
								}
							}
							clientDetails.setAllComments(allComments);*/

							/***************
							 * START UploadFilesEntity
							 *************/
							Set<UploadFilesEntity> uploadFilesEntitySet = taxFiledYearEntity.getUploadFilesEntityList();
							if (null != uploadFilesEntitySet) {

								LOGGER.info("getting existing UploadFilesEntity details");
								clientDetails.setUploadDocsList(prepareFilesDetailsForDownload(uploadFilesEntitySet));

							}
							clientModel.setClientDetails(clientDetails);
							
							
							Map<Long, ResidencyDetailsforStates> residencyDetailsforStatesSetModel = new HashMap<>();
							Map<Long, ResidencyDetailsforStates> spouseResidencyDetailsforStatesSetModel = new HashMap<>();
							Map<Long, ResidencyDetailsforStates> dependentResidencyDetailsforStatesSetModel = new HashMap<>();
							
							
							//List<ResidencyDetailsforStates> dependentResidencyDetailsforStatesSetModel = new ArrayList<>();
							Set<TaxYearInfo> taxYearInfoList = null;
							Set<TaxYearInfo> spouseTaxYearInfoList = null;
							Set<TaxYearInfo> dependentTaxYearInfoList = null;
							
							//get residency states details
							if (null != residencyDetailsForStatesEntitySet
									&& residencyDetailsForStatesEntitySet.size() > 0) {
																								
								ResidencyDetailsforStates basicModel = null;
								ResidencyDetailsforStates spouseModel = null;
								ResidencyDetailsforStates dependentModel = null;
								for (ResidencyDetailsForStatesEntity entity : residencyDetailsForStatesEntitySet) {
									if (entity.getTypeOfResidencyDetails().equalsIgnoreCase("basic")) {
										if(null != residencyDetailsforStatesSetModel.get(entity.getTaxYear())){
											basicModel = residencyDetailsforStatesSetModel.get(entity.getTaxYear());
											taxYearInfoList = basicModel.getTaxYearInfoList();
										}else {
											basicModel = new ResidencyDetailsforStates();
											taxYearInfoList = new HashSet<>();
											basicModel.setTaxYear((int) entity.getTaxYear());
										}
										TaxYearInfo info = new TaxYearInfo();
										if (null != entity.getStartDate())
											info.setStartDate(convertDateToString(entity.getStartDate()));
										if (null != entity.getEndDate())
											info.setEndDate(convertDateToString(entity.getEndDate()));
										info.setStateResided(entity.getStatesResided());
										taxYearInfoList.add(info);
										basicModel.setTaxYearInfoList(taxYearInfoList);
										residencyDetailsforStatesSetModel.put(entity.getTaxYear(),basicModel);
									} else if (entity.getTypeOfResidencyDetails().equalsIgnoreCase("spouse")) {
										
										if(null != spouseResidencyDetailsforStatesSetModel.get(entity.getTaxYear())){
											spouseModel = spouseResidencyDetailsforStatesSetModel.get(entity.getTaxYear());
											spouseTaxYearInfoList = spouseModel.getTaxYearInfoList();
										}else {
											spouseModel = new ResidencyDetailsforStates();
											spouseTaxYearInfoList = new HashSet<>();
											spouseModel.setTaxYear((int) entity.getTaxYear());
										}
										TaxYearInfo info = new TaxYearInfo();
										if (null != entity.getStartDate())
											info.setStartDate(convertDateToString(entity.getStartDate()));
										if (null != entity.getEndDate())
											info.setEndDate(convertDateToString(entity.getEndDate()));
										info.setStateResided(entity.getStatesResided());
										spouseTaxYearInfoList.add(info);
										spouseModel.setTaxYearInfoList(spouseTaxYearInfoList);
										spouseResidencyDetailsforStatesSetModel.put(entity.getTaxYear(),spouseModel);
									}else if (entity.getTypeOfResidencyDetails().equalsIgnoreCase("dependent")) {
										if(null != dependentResidencyDetailsforStatesSetModel.get(entity.getTaxYear())){
											dependentModel = dependentResidencyDetailsforStatesSetModel.get(entity.getTaxYear());
											dependentTaxYearInfoList = dependentModel.getTaxYearInfoList();
										}else {
											dependentModel = new ResidencyDetailsforStates();
											dependentTaxYearInfoList = new HashSet<>();
											dependentModel.setTaxYear((int) entity.getTaxYear());
										}
										TaxYearInfo info = new TaxYearInfo();
										if (null != entity.getStartDate())
											info.setStartDate(convertDateToString(entity.getStartDate()));
										if (null != entity.getEndDate())
											info.setEndDate(convertDateToString(entity.getEndDate()));
										info.setStateResided(entity.getStatesResided());
										dependentTaxYearInfoList.add(info);
										dependentModel.setTaxYearInfoList(dependentTaxYearInfoList);
										dependentResidencyDetailsforStatesSetModel.put(entity.getTaxYear(),dependentModel);
									}
								}							
							}
							
							/***************
							 * START DependentInformationEntity
							 *************/
							Set<DependentInformationEntity> dependentInformationEntityList = taxFiledYearEntity
									.getDependentInformationList();
							if (null != dependentInformationEntityList && !dependentInformationEntityList.isEmpty()) {

								LOGGER.info("getting existing dependentInformation details");
								
								for (DependentInformationEntity dependentInformationEntity : dependentInformationEntityList) {
									DependentInformation dependentInformation = new DependentInformation();
									Name name = new Name();
									name.setFirstName(dependentInformationEntity.getFirstName());
									name.setMiddleName(dependentInformationEntity.getMiddleName());
									name.setLastName(dependentInformationEntity.getLastName());
									dependentInformation.setName(name);
									dependentInformation.setSsnOrItin(dependentInformationEntity.getSsnitin());
									dependentInformation.setId(dependentInformationEntity.getId());

									if (null != dependentInformationEntity.getDateOfBirth())
										dependentInformation.setDateOfBirth(
												convertDateToString(dependentInformationEntity.getDateOfBirth()));

									dependentInformation.setCheckIfITINToBeApplied(
											dependentInformationEntity.isCheckIfITINToBeApplied());
									dependentInformation.setCheckIfITINToBeRenewed(
											dependentInformationEntity.isCheckIfITINToBeRenewed());
									dependentInformation.setITINRenewed(dependentInformationEntity.isITINRenewed());

									dependentInformation.setRelationship(dependentInformationEntity.getRelationship());
									dependentInformation.setVisaStatus(dependentInformationEntity.getVisaStatus());
									dependentInformation.setIfYouAndYourSpouseAreWorking(
											dependentInformationEntity.isYouAndSpouseWorking());
									dependentInformation.setLivingMoreThan6Months(
											dependentInformationEntity.isLivedForMoreThan06Months());
									dependentInformation.setIfProvidedMoreThan50PERSupportDuringTheYearXX(
											dependentInformation.isIfProvidedMoreThan50PERSupportDuringTheYearXX());
									dependentInformation
											.setNoOfDaysStayedInUS(dependentInformationEntity.getNoOfDaysStayedInUS());
									dependentInformation.setResidencyDetailsforStates(
											new ArrayList<>(dependentResidencyDetailsforStatesSetModel.values()));

									// setting daycare details
									DayCareEntity dayCareEntity = null;
									DayCareModel dayCareModel = null;
									if (null != dependentInformationEntity.getDayCareEntity()) {
										dayCareModel = new DayCareModel();
										dayCareEntity = dependentInformationEntity.getDayCareEntity();
										dayCareModel.setAddress(dayCareEntity.getAddress());
										dayCareModel.setInstName(dayCareEntity.getInstName());
										dayCareModel.setInstTaxId(dayCareEntity.getInstTaxId());
										dayCareModel.setDoorNo(dayCareEntity.getDoorNo());
										dayCareModel.setCity(dayCareEntity.getCity());
										dayCareModel.setState(dayCareEntity.getState());
										dayCareModel.setZip(dayCareEntity.getZip());
									}
									dependentInformation.setDayCareDetails(dayCareModel);
									dependentInformationList.add(dependentInformation);
								}
								clientModel.setDependentInformation(dependentInformationList);
							} // End of dependencyInfo

							/***************
							 * START OtherIncomeInformatonEntity
							 *************/

							Set<OtherIncomeInformatonEntity> otherIncomeInformatonEntitySet = taxFiledYearEntity
									.getOtherIncomeInformatonEntityList();
							if (null != otherIncomeInformatonEntitySet) {

								LOGGER.info("getting existing OtherIncomeInformatonEntity details");

								for (OtherIncomeInformatonEntity otherIncomeInformatonEntity : otherIncomeInformatonEntitySet) {
									OtherIncomeInfoData otherIncomeInfoData = new OtherIncomeInfoData();
									otherIncomeInfoData.setQuestion(otherIncomeInformatonEntity.getQuestion());
									otherIncomeInfoData.setAnswer(otherIncomeInformatonEntity.getAnswer());
									otherIncomeInfoData.setComments(otherIncomeInformatonEntity.getComments());
									otherIncomeInfoDataList.add(otherIncomeInfoData);
								}
								otherIncomeInfoModel.setOtherInfoDataList(otherIncomeInfoDataList);
								clientModel.setOtherIncomeInfoModel(otherIncomeInfoModel);
							} // End of otherIncomeInfo

							/***************
							 * START AdditionalInformationEntity
							 *************/

							Set<AdditionalInformationEntity> additionalInformatonEntitySet = taxFiledYearEntity
									.getAdditionalInformationEntityList();
							if (null != additionalInformatonEntitySet) {

								LOGGER.info("getting existing AdditionalInformationEntity details");

								for (AdditionalInformationEntity additionalInformationEntity : additionalInformatonEntitySet) {
									OtherIncomeInfoData otherIncomeInfoData = new OtherIncomeInfoData();
									otherIncomeInfoData.setQuestion(additionalInformationEntity.getQuestion());
									otherIncomeInfoData.setAnswer(additionalInformationEntity.getAnswer());
									otherIncomeInfoData.setComments(additionalInformationEntity.getComments());
									additionalInfoDataList.add(otherIncomeInfoData);
								}
								additionalInfoModel.setAdditionalInfoDataList(additionalInfoDataList);
								clientModel.setAdditionalInfoModel(additionalInfoModel);

							} // End of AdditionalInformation

							/***************
							 * START OtherInformationEntity
							 *************/

							OtherInformationEntity otherInformationEntity = taxFiledYearEntity
									.getOtherInformationEntity();
							if (null != otherInformationEntity) {

								LOGGER.info("getting existing OtherInformationEntity details");
								otherInfo.setOtherinformation(otherInformationEntity.getOtherInformation());
								clientModel.setOtherInformation(otherInfo);
							}

							/*************** START FbarEntity *************/
							FbarEntity fbarEntity = taxFiledYearEntity.getFbarEntity();
							if (null != fbarEntity) {

								LOGGER.info("getting existing fbarEntity details");
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
								clientModel.setFbar(fbar);
							} // End of Fbr

							/*************** START bank details *************/

							BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
							if (null != bankDetailsEntity) {

								LOGGER.info("getting existing BankDetailsEntity details");
								bankDetails.setUsBankAccountNumber(bankDetailsEntity.getBankAccountNumber());
								bankDetails.setTypeOfAccount(bankDetailsEntity.getBankAccountTpe());
								bankDetails.setNameOfTheBank(bankDetailsEntity.getBankName());
								bankDetails.setBankRoutingNumber(bankDetailsEntity.getRoutingNumber());
								bankDetails.setNameOfTheAccount(bankDetailsEntity.getNameOfTheAccount());
								clientModel.setBankDetails(bankDetails);
							} // End of BankDetailsEntity

							/***************
							 * START BasicInfoEntity details
							 *************/

							BasicInfoEntity basicInfoEntity = taxFiledYearEntity.getBasicInfo();
							if (null != basicInfoEntity) {

								LOGGER.info("getting existing basicInfoEntity details");
								basicInformation.setCitizenship(basicInfoEntity.getCitizenship());
								if (null != basicInfoEntity.getDob())
									basicInformation.setDateOfBirth(convertDateToString(basicInfoEntity.getDob()));
								if (null != basicInfoEntity.getDateOfMarriage())
									basicInformation.setDateOfMarriage(
											convertDateToString(basicInfoEntity.getDateOfMarriage()));
								if (null != basicInfoEntity.getFirstDateOfEntryInUS())
									basicInformation.setFirstDateOfEntyInUS(
											convertDateToString(basicInfoEntity.getFirstDateOfEntryInUS()));
								basicInformation.setFilingStatus(basicInfoEntity.getFilingStatus());
								Name name = new Name();
								name.setFirstName(basicInfoEntity.getFirstName());
								name.setLastName(basicInfoEntity.getLastName());
								name.setMiddleName(basicInfoEntity.getMiddleName());
								basicInformation.setName(name);
								basicInformation.setOccupation(basicInfoEntity.getOccupation());
								basicInformation.setSsn(basicInfoEntity.getSsn());
								basicInformation.setTypeOfVisa(basicInfoEntity.getTypeOfVisa());
								basicInformation.setDidYouWorkMoreThanOneEmployerInXX(
										basicInfoEntity.isDidYouWorkMoreThanOneEmployerInXX());
								basicInformation.setMoreThanOneEmployerWorkStatusComments(
										basicInfoEntity.getMoreThanOneEmployerWorkStatusComments());
								//basicInformation.setTimezone(basicInfoEntity.getTimezone());
								clientModel.setBasicInformation(basicInformation);
							} // End of BasicInfoEntity

							/***************
							 * START ContactDetailsEntity details
							 *************/

							ContactDetailsEntity contactDetailsEntity = taxFiledYearEntity.getContactDetails();
							if (null != contactDetailsEntity) {

								LOGGER.info("getting existing ContactDetailsEntity details");
								contactDetails.setAddress(contactDetailsEntity.getAddress());
								contactDetails.setCity(contactDetailsEntity.getCity());
								contactDetails.setAptNo(contactDetailsEntity.getAptNo());
								contactDetails.setState(contactDetailsEntity.getState());
								contactDetails.setZip(contactDetailsEntity.getZip());
								contactDetails.setCountry(contactDetailsEntity.getCountry());
								contactDetails.setMobilePhone(contactDetailsEntity.getMobilePhone());
								contactDetails.setIndiaNumber(contactDetailsEntity.getIndiaNumber());
								contactDetails.setEmail(contactDetailsEntity.getEmail());
								contactDetails.setTimezone(contactDetailsEntity.getTimezone());
								contactDetails.setState(contactDetailsEntity.getState());
								contactDetails.setAddressOfLivingInTaxYear(new HashSet<>(residencyDetailsforStatesSetModel.values()));

								clientModel.setContactDetails(contactDetails);
							} // End of ContactDetails

							/***************
							 * START SpouseDetailsEntity details
							 *************/

							SpouseDetailsEntity spouseDetailsEntity = taxFiledYearEntity.getSpouseDetails();
							if (null != spouseDetailsEntity) {

								LOGGER.info("getting existing spouseDetailsEntity details");
								Name name = new Name();
								name.setFirstName(spouseDetailsEntity.getFirstName());
								name.setLastName(spouseDetailsEntity.getLastName());
								name.setMiddleName(spouseDetailsEntity.getMiddleName());
								spouseDetails.setName(name);

								spouseDetails.setSsnOrItin(spouseDetailsEntity.getSsnOrItin());

								if (null != spouseDetailsEntity.getDateOfBirth())
									spouseDetails
											.setDateOfBirth(convertDateToString(spouseDetailsEntity.getDateOfBirth()));

								spouseDetails.setCheckIfITINToBeApplied(spouseDetailsEntity.isCheckIfITINToBeApplied());
								spouseDetails.setCheckIfITINToBeRenewed(spouseDetailsEntity.isCheckIfITINToBeRenewed());
								spouseDetails.setITINRenewed(spouseDetailsEntity.isITINRenewed());

								if (null != spouseDetailsEntity.getEntryDateIntoUS())
									spouseDetails.setEntryDateIntoUS(
											convertDateToString(spouseDetailsEntity.getEntryDateIntoUS()));
								spouseDetails.setOccupation(spouseDetailsEntity.getOccupation());
								spouseDetails.setLivingMoreThan6Months(spouseDetailsEntity.isLivingMoreThan6Months());
								spouseDetails.setDidYourSpouseisWorkedinXX(
										spouseDetailsEntity.isDidYourSpouseisWorkedinXX());
								//spouseDetails.setTypeOfVisa(spouseDetailsEntity.getTypeOfVisa());

								if (spouseDetails.isDidYourSpouseisWorkedinXX()) {
									spouseDetailsEntity
											.setSpouseWorkStatusComments(spouseDetails.getSpouseWorkStatusComments());
								}
								spouseDetailsEntity.setDidYouWorkMoreThanOneEmployerInXX(
										spouseDetails.isDidYouWorkMoreThanOneEmployerInXX());
								if (spouseDetails.isDidYouWorkMoreThanOneEmployerInXX()) {
									spouseDetailsEntity.setMoreThanOneEmployerWorkStatusComments(
											spouseDetails.getMoreThanOneEmployerWorkStatusComments());
								}
								spouseDetails.setAddressOfLivingInTaxYear(new HashSet<>(spouseResidencyDetailsforStatesSetModel.values()));
								clientModel.setSpouseDetails(spouseDetails);
							} // End of SpouseDetails
								// setting rental income details
							RentalIncomeModel rentalIncomeModelObj = null;
							if (null != taxFiledYearEntity.getRentalIncome()) {
								RentalIncomeEntity responseModel = taxFiledYearEntity.getRentalIncome();
								String rentalIncomeEntityStr = taxfilerUtil.convertObjectTOString(responseModel);
								rentalIncomeModelObj = (RentalIncomeModel) taxfilerUtil
										.convertStringToObject(rentalIncomeEntityStr, RentalIncomeModel.class);
								if (null != responseModel.getDateOfPropertyPurchased())
									rentalIncomeModelObj.setDateOfPropertyPurchased(
											convertDateToString(responseModel.getDateOfPropertyPurchased()));
							}
							clientModel.setRentalIncomeModel(rentalIncomeModelObj);

							// setting expenses&contribution model
							ExpensesAndConntributionResponseModel responseModel = null;
							if (null != taxFiledYearEntity.getExpensesEntityList()
									|| null != taxFiledYearEntity.getContributionEntityList()) {
								responseModel = new ExpensesAndConntributionResponseModel();
								responseModel.setExpensesInfoList(taxFiledYearEntity.getExpensesEntityList());
								responseModel.setContributionInfoList(taxFiledYearEntity.getContributionEntityList());
							}
							clientModel.setExpensesAndContributionModel(responseModel);

							// setting main&sub status
							clientDetails.setMainStatus(taxFiledYearEntity.getMainStatus());
							clientDetails.setSubStatus(taxFiledYearEntity.getSubStatus());
							
							//setting comments data
							clientModel.setCommentsList(taxFiledYearEntity.getCommentsList());
							
						} // End of TaxFilerEntity

					} // End of for loop of taxFiledYearEntityList

					return clientModel;
				} else {
					return taxfilerUtil.getErrorResponse(MessageCode.TAX_NOT_FILED_THIS_YEAR);
				}

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED);
		}
	}

	public List<DownloadModel> prepareFilesDetailsForDownload(Set<UploadFilesEntity> uploadFilesEntitySet)
			throws Exception {
		List<DownloadModel> downloadFileModelList = new ArrayList<>();
		try {
			for (UploadFilesEntity uploadFilesEntity : uploadFilesEntitySet) {
				DownloadModel downloadModel = new DownloadModel();
				downloadModel.setDate(uploadFilesEntity.getDate());
				downloadModel.setDownloadLink(uploadFilesEntity.getDownloadId());
				downloadModel.setFileName(uploadFilesEntity.getFileName());
				downloadModel.setFileType(uploadFilesEntity.getReqFileType());
				downloadFileModelList.add(downloadModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("an error has occured");
		}
		return downloadFileModelList;
	}

	public String convertDateToString(Date date) {
		String strDate = format.format(date);

		return strDate;
	}

	@PutMapping(Constants.UPDATE_USER_INFO_STATUS_BY_EMPLOYEE_ENDPOINT)
	public Object updateClientStatusByEmployee(@RequestBody ClientDetails clientDetails,
			@PathVariable(Constants.USER_ID) int userId, @PathVariable(Constants.TAX_YEAR) int taxYear)
			throws IOException {

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;

		if (!StringUtils.isNotBlank(clientDetails.getMainStatus())
				|| !StringUtils.isNotBlank(clientDetails.getSubStatus())) {
			return taxfilerUtil.getErrorResponse(MessageCode.MAINSTATUS_OR_SUBSTATUS_NULL_OR_EMPTY);
		}

		Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
			if (null != taxFiledYearEntityList && !taxFiledYearEntityList.isEmpty()) {
				for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
					if (taxFiledYearEntity.getYear() == taxYear) {
						LOGGER.info("updating existing userEntity details");
						userEntity.setName(clientDetails.getName());
						userEntity.setAlternatePhone(clientDetails.getAltPhone());
						// userEntity.setEmail(clientDetails.getEmail());
						userEntity.setPhone(clientDetails.getRegPhone());
						userEntity.setTaxFiledYearList(taxFiledYearEntityList);

						taxFiledYearEntity.setMainStatus(clientDetails.getMainStatus());
						taxFiledYearEntity.setSubStatus(clientDetails.getSubStatus());

						taxFiledYearEntity.setUserEntity(userEntity);

						userRepository.save(userEntity);
						return taxfilerUtil.getSuccessResponse(Constants.SUCCESS);
					}
				}
			}
		} else {
			return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
		}
		return taxfilerUtil.getSuccessResponse("no records were updated");
	}
}
