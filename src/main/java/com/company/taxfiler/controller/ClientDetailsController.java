package com.company.taxfiler.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.AdditionalInfoModel;
import com.company.model.BankDetails;
import com.company.model.BasicInformation;
import com.company.model.ClientDetails;
import com.company.model.ClientModel;
import com.company.model.ContactDetails;
import com.company.model.DependentInformation;
import com.company.model.DownloadModel;
import com.company.model.Fbar;
import com.company.model.Name;
import com.company.model.OtherIncomeInfoData;
import com.company.model.OtherIncomeInfoModel;
import com.company.model.OtherInformation;
import com.company.model.ResidencyDetailsforStates;
import com.company.model.SpouseDetails;
import com.company.model.TaxYearInfo;
import com.company.taxfiler.dao.AdditionalInformationEntity;
import com.company.taxfiler.dao.BankDetailsEntity;
import com.company.taxfiler.dao.BasicInfoEntity;
import com.company.taxfiler.dao.ContactDetailsEntity;
import com.company.taxfiler.dao.DependentInformationEntity;
import com.company.taxfiler.dao.FbarEntity;
import com.company.taxfiler.dao.MessagesEntity;
import com.company.taxfiler.dao.OtherIncomeInformatonEntity;
import com.company.taxfiler.dao.OtherInformationEntity;
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
@RequestMapping("/api")
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

		DependentInformation dependentInformation = new DependentInformation();
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
		Set<OtherIncomeInfoData> additionalInfoDataList = new HashSet<>();

		/**
		 * 1. validate sessionId 2. Get the all person details through the
		 * BUSINESS LOGIC 3.Get details related to file/messageId 4. Else show
		 * the error message
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
							List<String> allComments = null;
							Set<MessagesEntity> messagesEntitySet = taxFiledYearEntity.getMessagesEntityList();
							if (null != messagesEntitySet) {
								allComments = new ArrayList<String>();
								LOGGER.info("getting existing MessagesEntity details");
								for (MessagesEntity entity : messagesEntitySet) {

									allComments.add(entity.getMessage());
								}
							}
							clientDetails.setAllComments(allComments);

							/***************
							 * START UploadFilesEntity
							 *************/
							Set<UploadFilesEntity> uploadFilesEntitySet = taxFiledYearEntity.getUploadFilesEntityList();
							if (null != uploadFilesEntitySet) {

								LOGGER.info("getting existing UploadFilesEntity details");
								clientDetails.setUploadDocsList(prepareFilesDetailsForDownload(uploadFilesEntitySet));

							}
							clientModel.setClientDetails(clientDetails);
							/***************
							 * START DependentInformationEntity
							 *************/
							DependentInformationEntity dependentInformationEntity = taxFiledYearEntity
									.getDependentInformation();
							if (null != dependentInformationEntity) {

								LOGGER.info("getting existing dependentInformation details");

								Name name = new Name();
								name.setFirstName(dependentInformationEntity.getFirstName());
								name.setMiddleName(dependentInformationEntity.getMiddleName());
								name.setLastName(dependentInformationEntity.getLastName());
								dependentInformation.setName(name);
								dependentInformation.setSsnOrItin(dependentInformationEntity.getSsnitin());

								dependentInformation
										.setDateOfBirth(dependentInformationEntity.getDateOfBirth().toString());

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

								if (null != residencyDetailsForStatesEntitySet
										&& residencyDetailsForStatesEntitySet.size() > 0) {
									List<ResidencyDetailsforStates> residencyDetailsforStatesList = new ArrayList<>();
									Set<TaxYearInfo> taxYearInfoList = new HashSet<>();
									ResidencyDetailsforStates residencyDetailsforStates = new ResidencyDetailsforStates();
									for (ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity : residencyDetailsForStatesEntitySet) {
										if (residencyDetailsForStatesEntity.getTypeOfResidencyDetails()
												.equals("dependent")) {
											residencyDetailsforStates.setTaxYear(year);

											TaxYearInfo taxYearInfo = new TaxYearInfo();
											taxYearInfo.setEndDate(
													residencyDetailsForStatesEntity.getEndDate().toString());
											taxYearInfo.setStartDate(
													residencyDetailsForStatesEntity.getStartDate().toString());
											taxYearInfo.setStateResided(
													residencyDetailsForStatesEntity.getStatesResided());
											taxYearInfoList.add(taxYearInfo);

											residencyDetailsforStates.setTaxYearInfoList(taxYearInfoList);
										}
									}
									residencyDetailsforStatesList.add(residencyDetailsforStates);
									dependentInformation.setResidencyDetailsforStates(residencyDetailsforStatesList);
								}
								clientModel.setDependentInformation(dependentInformation);
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
								clientModel.setFbar(fbar);
							} // End of Fbr

							/*************** START bank details *************/

							BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
							if (null != bankDetailsEntity) {

								LOGGER.info("getting existing BankDetailsEntity details");
								bankDetails.setUSBankAccountNumber(bankDetailsEntity.getBankAccountNumber());
								bankDetails.setTypeOfAccount(bankDetailsEntity.getBankAccountTpe());
								bankDetails.setNameOfTheBank(bankDetailsEntity.getBankName());
								bankDetails.setBankRoutingNumber(bankDetailsEntity.getRoutingNumber());
								clientModel.setBankDetails(bankDetails);
							} // End of BankDetailsEntity

							/***************
							 * START BasicInfoEntity details
							 *************/

							BasicInfoEntity basicInfoEntity = taxFiledYearEntity.getBasicInfo();
							if (null != basicInfoEntity) {

								LOGGER.info("getting existing basicInfoEntity details");
								basicInformation.setCitizenship(basicInfoEntity.getCitizenship());
								basicInformation.setDateOfBirth(convertDateToString(basicInfoEntity.getDob()));
								basicInformation
										.setDateOfMarriage(convertDateToString(basicInfoEntity.getDateOfMarriage()));
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

								if (null != residencyDetailsForStatesEntitySet
										&& residencyDetailsForStatesEntitySet.size() > 0) {
									Set<ResidencyDetailsforStates> residencyDetailsforStatesList = new HashSet<>();
									Set<TaxYearInfo> taxYearInfoList = new HashSet<>();
									ResidencyDetailsforStates residencyDetailsforStates = new ResidencyDetailsforStates();
									for (ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity : residencyDetailsForStatesEntitySet) {
										if (residencyDetailsForStatesEntity.getTypeOfResidencyDetails()
												.equals("basic")) {
											residencyDetailsforStates.setTaxYear(year);

											TaxYearInfo taxYearInfo = new TaxYearInfo();
											taxYearInfo.setEndDate(
													residencyDetailsForStatesEntity.getEndDate().toString());
											taxYearInfo.setStartDate(
													residencyDetailsForStatesEntity.getStartDate().toString());
											taxYearInfo.setStateResided(
													residencyDetailsForStatesEntity.getStatesResided());
											taxYearInfoList.add(taxYearInfo);

											residencyDetailsforStates.setTaxYearInfoList(taxYearInfoList);
										}
									}
									residencyDetailsforStatesList.add(residencyDetailsforStates);
									contactDetails.setAddressOfLivingInTaxYear(residencyDetailsforStatesList);

								}

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

								spouseDetails.setDateOfBirth(convertDateToString(spouseDetailsEntity.getDateOfBirth()));

								spouseDetails.setCheckIfITINToBeApplied(spouseDetailsEntity.isCheckIfITINToBeApplied());
								spouseDetails.setCheckIfITINToBeRenewed(spouseDetailsEntity.isCheckIfITINToBeRenewed());
								spouseDetails.setITINRenewed(spouseDetailsEntity.isITINRenewed());

								spouseDetails.setEntryDateIntoUS(
										convertDateToString(spouseDetailsEntity.getEntryDateIntoUS()));
								spouseDetails.setOccupation(spouseDetailsEntity.getOccupation());
								spouseDetails.setLivingMoreThan6Months(spouseDetailsEntity.isLivingMoreThan6Months());
								spouseDetails.setDidYourSpouseisWorkedinXX(
										spouseDetailsEntity.isDidYourSpouseisWorkedinXX());

								if (null != residencyDetailsForStatesEntitySet
										&& residencyDetailsForStatesEntitySet.size() > 0) {
									Set<ResidencyDetailsforStates> residencyDetailsforStatesList = new HashSet<>();
									Set<TaxYearInfo> taxYearInfoList = new HashSet<>();
									ResidencyDetailsforStates residencyDetailsforStates = new ResidencyDetailsforStates();
									for (ResidencyDetailsForStatesEntity residencyDetailsForStatesEntity : residencyDetailsForStatesEntitySet) {
										if (residencyDetailsForStatesEntity.getTypeOfResidencyDetails()
												.equals("spouse")) {
											residencyDetailsforStates.setTaxYear(year);

											TaxYearInfo taxYearInfo = new TaxYearInfo();
											taxYearInfo.setEndDate(
													residencyDetailsForStatesEntity.getEndDate().toString());
											taxYearInfo.setStartDate(
													residencyDetailsForStatesEntity.getStartDate().toString());
											taxYearInfo.setStateResided(
													residencyDetailsForStatesEntity.getStatesResided());
											taxYearInfoList.add(taxYearInfo);

											residencyDetailsforStates.setTaxYearInfoList(taxYearInfoList);
										}

									}
									residencyDetailsforStatesList.add(residencyDetailsforStates);
									spouseDetails.setAddressOfLivingInTaxYear(residencyDetailsforStatesList);
								}
								clientModel.setSpouseDetails(spouseDetails);
							} // End of SpouseDetails
						} // End of TaxFilerEntity

					} // End of for loop of taxFiledYearEntityList

					return clientModel;
				} else {
					return " Tax not filed for this year";
				}

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED);
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
}
