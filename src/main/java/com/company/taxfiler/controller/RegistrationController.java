package com.company.taxfiler.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.SettingsModel;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.email.Email2;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.model.ResponseModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.Constants;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;
import com.google.gson.Gson;

@RestController
@RequestMapping(Constants.API)
public class RegistrationController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaxfilerUtil taxfilerUtil;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping(Constants.POST_REGISTER_USER_ENDPOINT)
	public Object registerUser(@RequestBody RegistraionModel registraionModel) throws IOException {
		/**
		 * 1. do validations 2. insert into database
		 */

		/*
		 * Object validationResult =
		 * taxfilerUtil.validateRegistrationEndpointRequestParameters(registraionModel);
		 * if (validationResult == null) { //validation success, insert into database }
		 * else { //validation failed, return error response return validationResult; }
		 */

		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(registraionModel));

		// insert into db and respond with "registration completed successfully!"
		try {
			UserEntity userEntity = userRepository.findByEmail(registraionModel.getEmail());
			if (null == userEntity) {
				userEntity = new UserEntity();
				userEntity.setAlternatePhone(registraionModel.getAlternatePhone());
				userEntity.setPhone(registraionModel.getPhone());
				userEntity.setEmail(registraionModel.getEmail());
				userEntity.setName(registraionModel.getName());
				// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				// String hashedPassword =
				// passwordEncoder.encode(registraionModel.getPassword());
				// userEntity.setPassword(hashedPassword);
				userEntity.setPassword(registraionModel.getPassword());
				userEntity.setSourceOfKnowingSite(registraionModel.getSourceOfKnowingSite());
				userEntity.setTimezone(registraionModel.getPreferredTimezone());
				userEntity.setRole(registraionModel.getRole());

				/**
				 * adding current year entry into tax_file_year table
				 */
				Set<TaxFiledYearEntity> taxFiledYearEntityList = new HashSet<>();
				TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
				taxFiledYearEntity.setYear(Calendar.getInstance().get(Calendar.YEAR));
				taxFiledYearEntity.setUserEntity(userEntity);
				taxFiledYearEntityList.add(taxFiledYearEntity);
				userEntity.setTaxFiledYearList(taxFiledYearEntityList);

				userRepository.save(userEntity);

				String loginLink = "";
				if (registraionModel.getRole().equalsIgnoreCase("CLIENT")) {
					loginLink = "https://serenetax.com/client/";
				} else {
					loginLink = "https://serenetax.com/employee/";
				}

				/**
				 * preparing & sending email
				 */
				StringBuilder mailBody = new StringBuilder();

				mailBody.append("<p><h1>Welcome to SereneTax Family.</h1></p><p>Dear ")
						.append(registraionModel.getName() + ",").append(TaxfilerUtil.mailBodyStr).append("<b><p>")
						.append("USER NAME:  ").append(registraionModel.getEmail()).append("</p></b>")
						.append("<b><p>PASSWORD: ").append(registraionModel.getPassword()).append("</p></b>")
						//.append("<p><a href=" + loginLink + ">Click here</a> to log in Serenetax").append("</p>")
						.append("<h2><p>Reference number / File number: ").append(userEntity.getId()).append("</p></h2>")
						.append("<p>Please Login to your account & update your personal Information </p>")
						.append("<p>Our mission is to produce the highest quality work on every Individual. ")
						.append("</p>").append("<p>Step 1: Develop your information & Upload income documents ")
						.append("</p>").append("<p>Step 2: Check your tax summary for free within 24 hours. ")
						.append("</p>").append("<p>Step 3: Analyse your return and make payment. ").append("</p>")
						.append("<b><p>Thanks & Regards </p></b>").append("<p>Serenetax.LLC</p>")
						.append("<p>42707 Wardlaw Terrace Ashburn Virginia 20147 USA </p>")
						.append("<p>Contact: +1-262-735-8875, (USA) , +91-9966988875 (IND) </p>");
						//.append("<p>Email: contact@serenetax.com</p>")
						//.append("<p>Web: https://serenetax.com</p>");

				System.out.println(mailBody.toString().trim());
				// Email2.sendImageEmail(registraionModel.getEmail(), "Serenetax Registration",
				// mailBody.toString());
				Email2.sendEmail(registraionModel.getEmail().trim(), "Serenetax Registration",
						mailBody.toString().trim());

				return taxfilerUtil.getSuccessResponse("user created successfully");
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.EMAIL_IS_ALREADY_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
	}

	@PostMapping(Constants.POST_EDIT_PROFILE_ENDPOINT)
	public Object editProfile(@RequestBody SettingsModel settingsModel, @PathVariable(Constants.USER_ID) int userId)
			throws IOException {
		LOGGER.info("Entering into editProfile details");

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(settingsModel));

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();

				userEntity.setPhone(settingsModel.getPhone());
				userEntity.setName(settingsModel.getName());

				userRepository.save(userEntity);

				return taxfilerUtil.getSuccessResponse("Profile successfully updated");

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
	}

	@PostMapping(Constants.POST_CHANGE_PASSWORD_ENDPOINT)
	public Object changePassword(@RequestBody SettingsModel settingsModel, @PathVariable(Constants.USER_ID) int userId)
			throws IOException {
		LOGGER.info("Entering into changePassword details");

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(settingsModel));

		try {

			if (!settingsModel.getNewPassword().equals(settingsModel.getConfirmPassword())) {
				return taxfilerUtil.getErrorResponse(MessageCode.NEW_PASSWORD_NOT_MATCHED_WITH_CONFIRM_PASSWORD);
			}

			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				// if
				// (!passwordEncoder.matches(settingsModel.getCurrentPassword(),userEntity.getPassword()))
				// {
				if (!settingsModel.getCurrentPassword().equals(userEntity.getPassword())) {

					return taxfilerUtil
							.getErrorResponse(MessageCode.CURRENT_PASSWORD_IS_INVALID_PLEASE_TRY_WITH_VALID_PASSWORD);
				}

				// String hashedPassword =
				// passwordEncoder.encode(settingsModel.getCurrentPassword());
				// userEntity.setPassword(hashedPassword);
				userEntity.setPassword(settingsModel.getNewPassword());

				userRepository.save(userEntity);
				return taxfilerUtil.getSuccessResponse("Change password succeeded");
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
	}

}
