package com.company.taxfiler.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.model.SettingsModel;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.repository.UserRepository;
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

	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@PostMapping("/register")
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
			if(null == userEntity) {
			userEntity = new UserEntity();
			userEntity.setAlternatePhone(registraionModel.getAlternatePhone());
			userEntity.setPhone(registraionModel.getPhone());
			userEntity.setEmail(registraionModel.getEmail());
			userEntity.setName(registraionModel.getName());
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//String hashedPassword = passwordEncoder.encode(registraionModel.getPassword());
			//userEntity.setPassword(hashedPassword);
			userEntity.setPassword(registraionModel.getPassword());
			userEntity.setSourceOfKnowingSite(registraionModel.getSourceOfKnowingSite());
			userEntity.setTimezone(registraionModel.getPreferredTimezone());
			userEntity.setRole(registraionModel.getRole());
			userRepository.save(userEntity);
			return "user created successfully";
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value(), "email is already registered");
				return "email is already registered";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@PostMapping("/settings/editProfile")
	public Object editProfile(@RequestBody SettingsModel settingsModel) throws IOException {
		LOGGER.info("Entering into editProfile details");
		JSONObject jsonResponse = new JSONObject();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(settingsModel));


		try {
			if(settingsModel.getEmail()!=null) {
			UserEntity userEntity = userRepository.findByEmail(settingsModel.getEmail());
			if(null != userEntity) {
				LOGGER.info(" DB Data data: " + userEntity.getEmail());


			userEntity.setPhone(settingsModel.getPhone());
			userEntity.setEmail(settingsModel.getEmail());
			userEntity.setName(settingsModel.getName());


			userRepository.save(userEntity);
			 return "Profile successfully updated";
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				jsonResponse.put("error", "user not registered");
				LOGGER.error(jsonResponse.toString());
				return jsonResponse.toString();
			}
			
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				jsonResponse.put("error", "Mail Id should not be null");
				LOGGER.error(jsonResponse.toString());
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	@PostMapping("/settings/changePassword")
	public Object changePassword(@RequestBody SettingsModel settingsModel) throws IOException {
		LOGGER.info("Entering into changePassword details");
		JSONObject jsonResponse = new JSONObject();

		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;
		// Insert into database
		Gson gson = new Gson();
		LOGGER.info("postman request data: " + gson.toJson(settingsModel));


		try {
			
			if(!settingsModel.getNewPassword().equals(settingsModel.getConfirmPassword())) {
				
				return "New password not matched with confirm password";
				
			}
			if(settingsModel.getEmail()!=null) {
								
			UserEntity userEntity = userRepository.findByEmail(settingsModel.getEmail());
			if(null != userEntity) {
				LOGGER.info(" DB Data data: " + userEntity.getEmail());
				 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				//if (!passwordEncoder.matches(settingsModel.getCurrentPassword(),userEntity.getPassword())) {
					if (!settingsModel.getCurrentPassword().equals(userEntity.getPassword())) {

					return "Current password is wrong.Please Try again with correct password";
				}

				//String hashedPassword = passwordEncoder.encode(settingsModel.getCurrentPassword());
				//userEntity.setPassword(hashedPassword);
					userEntity.setPassword(settingsModel.getNewPassword());

			userRepository.save(userEntity);
			 return "Change password succeeded";
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				jsonResponse.put("error", "user not registered");
				LOGGER.error(jsonResponse.toString());
				return jsonResponse.toString();
			}
			
			}else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				jsonResponse.put("error", "Mail Id should not be null");
				LOGGER.error(jsonResponse.toString());
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return null;
	}

	
	
}
