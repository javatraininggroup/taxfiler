package com.company.taxfiler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

	@GetMapping("/login")
	public Object loginUser(@RequestParam("email") String email,@RequestParam("password") String password) {
		
		/**
		 * 1. validate (username and password)
		 * 2. If username and password is correct go through the BUSINESS LOGIC
		 * 3. Else show the error message
		 */
		return null;
	}

}
