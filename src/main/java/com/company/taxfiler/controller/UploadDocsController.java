package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.UploadDocs;

@RestController
@RequestMapping("/api")
public class UploadDocsController {


	@PostMapping("/uploaddocs")
	public Object uploadDocs(@RequestBody UploadDocs uploadDocs) {

		/**
		 * 1. do validations
		 * 2. insert into database
		 */
		return null;
	}

}
