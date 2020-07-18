package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.TaxPayer;

@RestController("/api")
public class TaxFillingController {

	@PostMapping("/taxsummary")
	public Object taxSummary(@RequestBody TaxPayer taxpayer) {
		/**
		 * 1. do validations
		 * 2. insert into database
		 */
		return null;
	}

}
