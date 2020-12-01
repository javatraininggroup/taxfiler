package com.company.taxfiler.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.SettingsModel;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.model.ResponseModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.Constants;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
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
				userRepository.save(userEntity);
				return taxfilerUtil.getSuccessResponse("user created successfully");
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.EMAIL_IS_ALREADY_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@PostMapping(Constants.POST_EDIT_PROFILE_ENDPOINT)
	public Object editProfile(@RequestBody SettingsModel settingsModel) throws IOException {
		LOGGER.info("Entering into editProfile details");
		JSONObject jsonResponse = new JSONObject();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(settingsModel));

		try {
			if (settingsModel.getEmail() != null) {
				UserEntity userEntity = userRepository.findByEmail(settingsModel.getEmail());
				if (null != userEntity) {
					LOGGER.info(" DB Data data: " + userEntity.getEmail());

					userEntity.setPhone(settingsModel.getPhone());
					userEntity.setEmail(settingsModel.getEmail());
					userEntity.setName(settingsModel.getName());

					userRepository.save(userEntity);
					return taxfilerUtil.getSuccessResponse("Profile successfully updated");
				} else {
					return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
				}

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.EMAIL_NULL_OR_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@PostMapping(Constants.POST_CHANGE_PASSWORD_ENDPOINT)
	public Object changePassword(@RequestBody SettingsModel settingsModel) throws IOException {
		LOGGER.info("Entering into changePassword details");
		JSONObject jsonResponse = new JSONObject();

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
			if (settingsModel.getEmail() != null) {

				UserEntity userEntity = userRepository.findByEmail(settingsModel.getEmail());
				if (null != userEntity) {
					LOGGER.info(" DB Data data: " + userEntity.getEmail());
					//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					// if
					// (!passwordEncoder.matches(settingsModel.getCurrentPassword(),userEntity.getPassword()))
					// {
					if (!settingsModel.getCurrentPassword().equals(userEntity.getPassword())) {

						return taxfilerUtil.getErrorResponse(MessageCode.CURRENT_PASSWORD_IS_INVALID_PLEASE_TRY_WITH_VALID_PASSWORD);
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

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.EMAIL_NULL_OR_EMPTY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
