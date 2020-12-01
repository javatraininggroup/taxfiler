package com.company.taxfiler.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.Constants;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;

@RestController
@RequestMapping(Constants.API)
public class LoginController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private TaxfilerUtil taxfilerUtil;

	@PostMapping(Constants.POST_LOGIN_USER_ENDPOINT)
	public Object loginUser(@RequestBody RegistraionModel loginModel) {

		JSONObject jsonResponse = new JSONObject();

		/**
		 * 1. validate (username and password) 2. If username and password is correct go
		 * through the BUSINESS LOGIC 3. Else show the error message
		 */

		try {
			UserEntity userEntity = userRepository.findByEmail(loginModel.getEmail());
			if (null != userEntity) {
				// String generatedSecuredPasswordHash = BCrypt.hashpw(loginModel.getPassword(),
				// BCrypt.gensalt(12));
				// System.out.println(generatedSecuredPasswordHash);

				// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				// if
				// (!passwordEncoder.matches(loginModel.getPassword(),userEntity.getPassword()))
				// {

				if (userEntity.getPassword().equals(loginModel.getPassword())) {
					jsonResponse.put("username", userEntity.getName());
					jsonResponse.put("id", userEntity.getId());
					jsonResponse.put("email", userEntity.getEmail());
					jsonResponse.put("phone", userEntity.getPhone());
					jsonResponse.put("role", userEntity.getRole());

					/**
					 * generating a unique sessionId for each user
					 */
					String sessionId = taxfilerUtil.createHMAC256TokenWithEmptyPayload();
					TaxfilerUtil.getTtlhashmap().put(sessionId, userEntity.getEmail());
					if (userEntity.getRole().equalsIgnoreCase("EMPLOYEE")
							|| userEntity.getRole().equalsIgnoreCase("SUPER_ADMIN"))
						TaxfilerUtil.getTtlhashmap().getOtherSessionIds().add(sessionId);

					jsonResponse.put("sessionId", sessionId);

					return jsonResponse.toString();
				} else {
					return taxfilerUtil.getErrorResponse(MessageCode.EMAIL_OR_PASSWORD_NOT_MATCHED);
				}

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
//		 return "an error has occured";
	}

}
