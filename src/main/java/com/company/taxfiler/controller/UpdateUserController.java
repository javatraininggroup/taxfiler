package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.TaxPayer;

@RestController
@RequestMapping("/api")
public class UpdateUserController {

	@PutMapping("/updateuser/basicInfo")
	public Object updateUserBasicInfo(@RequestBody TaxPayer taxPayerModel) {
		/**
		 * 1. updateUser information if that parameter is exist
		 * 2. Modify the latest data in the database .
		 */
		return null;
	}
	@PutMapping("/updateuser/dependentInfo")
	public Object updateUserDependentInfo(@RequestBody TaxPayer taxPayerModel) {
		/**
		 * 1. updateUser information if that parameter is exist
		 * 2. Modify the latest data in the database .
		 */
		
		return null;
	}
	@PutMapping("/updateuser/bankInfo")
	public Object updateUserBankInfo(@RequestBody TaxPayer taxPayerModel) {
		/**
		 * 1. updateUser information if that parameter is exist
		 * 2. Modify the latest data in the database .
		 */
		return null;
	}

}
