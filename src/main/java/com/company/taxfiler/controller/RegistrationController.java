package com.company.taxfiler.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;

@RestController
@RequestMapping("/api")
public class RegistrationController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaxfilerUtil taxfilerUtil;

	@PostMapping("/register")
	public Object registerUser(@RequestBody RegistraionModel registraionModel) throws IOException {
		/**
		 * 1. do validations 2. insert into database
		 */
		if (!taxfilerUtil.checkStringNullEmptyWhiteSpace(registraionModel.getName())) {
			LOGGER.error("name parameter not passed validation");
			return taxfilerUtil.getErrorResponse(MessageCode.NAME_NULL_OR_EMPTY);
		} else {
			LOGGER.info("name parameter passed validation");
		}
		return null;
	}

}
