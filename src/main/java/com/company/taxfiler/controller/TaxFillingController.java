package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.TaxPayer;

@RestController("/api")
public class TaxFillingController {

	@PostMapping("/taxFilling")
	public Object taxFilling(@RequestBody TaxPayer taxpayer) {
		/**
		 * 1. do validations 2. insert into database
		 */
		return null;
	}

//	@GetMapping("/taxsummary")
//	public Object taxSummary(@RequestParam("nameAsPerSSN") String name) {
//		/**
//		 * 1. get the informaion from the database.
//		 * 2. show to the enduser
//		 */
//		return null;
//	}

}
