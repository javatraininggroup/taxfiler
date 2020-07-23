package com.company.taxfiler.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.BankDetails;
import com.company.model.BasicInformation;
import com.company.model.ContactDetails;
import com.company.model.ResidencyDetailsforStates;
import com.company.model.SpouseDetails;
import com.company.model.TaxPayer;
import com.company.model.TaxYearInfo;
import com.company.taxfiler.dao.BankDetailsEntity;
import com.company.taxfiler.dao.BasicInfoEntity;
import com.company.taxfiler.dao.ContactDetailsEntity;
import com.company.taxfiler.dao.ResidencyDetailsForStatesEntity;
import com.company.taxfiler.dao.SpouseDetailsEntity;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.repository.UserRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
public class UpdateUserController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/updateuser/basicInfo")
	public Object updateUserBasicInfo(@RequestBody TaxPayer taxPayerModel) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));

		try {

			BasicInformation basicInformationModel = taxPayerModel.getBasicInformation();
			UserEntity userEntity = new UserEntity();
			TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
			if (null != basicInformationModel) {
				BasicInfoEntity basicInfo = new BasicInfoEntity();
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

				java.util.Date reqFirstEntryDate = new java.util.Date(basicInformationModel.getFirstDateOfEntyInUS());
				Date dbFirstEntryDate = new Date(reqFirstEntryDate.getDate());
				basicInfo.setFirstDateOfEntryInUS(dbFirstEntryDate);

				basicInfo.setTypeOfVisa(basicInformationModel.getTypeOfVisa());
				basicInfo.setCitizenship(basicInformationModel.getCitizenship());

				taxFiledYearEntity.setBasicInfo(basicInfo);
			}
			ContactDetails contactDetails = taxPayerModel.getContactDetails();
			if (null != contactDetails) {
				ContactDetailsEntity ContactDetailsEntity = new ContactDetailsEntity();
				ContactDetailsEntity.setAddress(contactDetails.getAddress());
				ContactDetailsEntity.setCity(contactDetails.getCity());
				ContactDetailsEntity.setAptNo(contactDetails.getAptNo());
				ContactDetailsEntity.setState(contactDetails.getState());
				ContactDetailsEntity.setZip(contactDetails.getZip());
				ContactDetailsEntity.setCountry(contactDetails.getCountry());
				ContactDetailsEntity.setMobilePhone(contactDetails.getMobilePhone());
				ContactDetailsEntity.setAlternatePhone(contactDetails.getAlternateNumber());
				ContactDetailsEntity.setIndiaNumber(contactDetails.getIndiaNumber());
				ContactDetailsEntity.setEmail(contactDetails.getEmail());
				ContactDetailsEntity.setTimezone(contactDetails.getTimezone());

				if (null != contactDetails.getResidencyDetailsforStates()
						&& contactDetails.getResidencyDetailsforStates().size() > 0) {
					List<ResidencyDetailsForStatesEntity> residencyDetailsForStatesEntityList = new ArrayList<>();
					List<ResidencyDetailsforStates> residencyDetailsforStatesList = contactDetails
							.getResidencyDetailsforStates();
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

							residencyDetailsForStatesEntityList.add(residencyDetailsForStatesEntity);
						}
					}
					taxFiledYearEntity.setResidencyDetailsforStatesList(residencyDetailsForStatesEntityList);
				}
				taxFiledYearEntity.setContactDetails(ContactDetailsEntity);
			}
			if (null != taxPayerModel.getSpouseDetails()) {
				SpouseDetails spouseDetails = taxPayerModel.getSpouseDetails();

				SpouseDetailsEntity spouseDetailsEntity = new SpouseDetailsEntity();
				spouseDetailsEntity.setFirstName(spouseDetails.getName().getFirstName());
				spouseDetailsEntity.setMiddleName(spouseDetails.getName().getMiddleName());
				spouseDetailsEntity.setLastName(spouseDetails.getName().getLastName());
				spouseDetailsEntity.setSsnOrItin(spouseDetails.getSsnOrItin());

				java.util.Date reqDobDate = new java.util.Date(spouseDetails.getDateOfBirth());
				Date dbDobDate = new Date(reqDobDate.getDate());
				spouseDetailsEntity.setDateOfBirth(dbDobDate);

				spouseDetailsEntity.setCheckIfITINToBeApplied(spouseDetails.isCheckIfITINToBeApplied());

			}

			userEntity.setTaxFiledYearList(Arrays.asList(taxFiledYearEntity));
			userRepository.save(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@PutMapping("/updateuser/dependentInfo")
	public Object updateUserDependentInfo(@RequestBody TaxPayer taxPayerModel) {
		/**
		 * 1. updateUser information if that parameter is exist 2. Modify the latest
		 * data in the database .
		 */
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(taxPayerModel));
		return null;
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

			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				List<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == 2019) {
							LOGGER.info("updating existing bank details");
							BankDetailsEntity bankDetailsEntity = taxFiledYearEntity.getBankDetails();
							bankDetailsEntity.setBankAccountNumber(bankDetails.getBankAccountNumber());
							bankDetailsEntity.setBankAccountTpe(bankDetails.getBankAccountType());
							bankDetailsEntity.setBankName(bankDetails.getBankName());
							bankDetailsEntity.setRoutingNumber(bankDetails.getRoutingNumber());

							userRepository.save(userEntity);
							LOGGER.info("bank details updated successfully!");
						}
					}
				} else {
					LOGGER.info("inserting into db as no records found");

					taxFiledYearEntityList = new ArrayList<>();
					TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
					taxFiledYearEntity.setYear(2019);

					BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
					bankDetailsEntity.setBankAccountNumber(bankDetails.getBankAccountNumber());
					bankDetailsEntity.setBankAccountTpe(bankDetails.getBankAccountType());
					bankDetailsEntity.setBankName(bankDetails.getBankName());
					bankDetailsEntity.setRoutingNumber(bankDetails.getRoutingNumber());

					bankDetailsEntity.setTaxFileYear(taxFiledYearEntity);
					taxFiledYearEntity.setBankDetails(bankDetailsEntity);
					taxFiledYearEntity.setUserEntity(userEntity);
					taxFiledYearEntityList.add(taxFiledYearEntity);
					userEntity.setTaxFiledYearList(taxFiledYearEntityList);
					userRepository.save(userEntity);
				}

			} else {
				return "user not found";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@PutMapping("/updateuser/{user_id}/manojOtherInfo")
	public String manjoOtherInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId) {
		return null;
	}
	
	@PutMapping("/updateuser/{user_id}/nikhilOtherInfo")
	public String nikhilOtherInformation(@RequestBody TaxPayer taxPayerModel, @PathVariable("user_id") int userId) {
		return null;
	}

}
