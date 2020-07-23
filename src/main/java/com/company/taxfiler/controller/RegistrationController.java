package com.company.taxfiler.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
			UserEntity userEntity = new UserEntity();
			userEntity.setAlternatePhone(registraionModel.getAlternatePhone());
			userEntity.setPhone(registraionModel.getPhone());
			userEntity.setEmail(registraionModel.getEmail());
			userEntity.setName(registraionModel.getName());
			userEntity.setPassword(registraionModel.getPassword());
			userEntity.setSourceOfKnowingSite(registraionModel.getSourceOfKnowingSite());
			userEntity.setTimezone(registraionModel.getPreferredTimezone());
			userRepository.save(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
