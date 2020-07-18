package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.RegistraionModel;

@RestController("/api")
public class UpdateUserController {

	@PutMapping("/updateuser")
	public Object updateUser(@RequestBody RegistraionModel registraionModel) {
		/**
		 * 1. updateUser information if that parameter is exist
		 * 2. Modify the latest data in the database .
		 */
		return null;
	}

}
