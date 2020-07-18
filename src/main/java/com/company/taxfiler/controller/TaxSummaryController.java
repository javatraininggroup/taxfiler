package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.RegistraionModel;

@RestController("/api")
public class TaxSummaryController {

	@PostMapping("/taxsummary")
	public Object taxSummary(@RequestBody RegistraionModel registraionModel) {
		/**
		 * 1. do validations
		 * 2. insert into database
		 */
		return null;
	}

}
