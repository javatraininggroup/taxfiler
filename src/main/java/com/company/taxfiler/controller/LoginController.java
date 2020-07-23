package com.company.taxfiler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public Object loginUser(@RequestBody RegistraionModel loginModel) {
		
		/**
		 * 1. validate (username and password)
		 * 2. If username and password is correct go through the BUSINESS LOGIC
		 * 3. Else show the error message
		 */
		try {
			UserEntity userEntity = userRepository.findByEmail(loginModel.getEmail());
			if(null != userEntity) {
				if(userEntity.getPassword().equals(loginModel.getPassword())){
					return "login success";
				}else {
					return "username/password dont match";
				}
			}else {
				return "user not found";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
