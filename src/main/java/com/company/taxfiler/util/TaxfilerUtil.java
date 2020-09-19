package com.company.taxfiler.util;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.model.ResponseModel;

@Component
public class TaxfilerUtil {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private final String HMACSHA256 = "HmacSHA256";

	private static final TtlHashMap ttlHashMap = new TtlHashMap(TimeUnit.MINUTES, 60);

	// PassiveExpiringMap passiveExpiringMap = new PassiveExpiringMap();

	@Autowired
	private HttpServletResponse httpServletResponse;

	public boolean checkStringNullEmptyWhiteSpace(String param) {
		if (!StringUtils.isNotBlank(param)) {
			return false;
		}
		return true;
	}

	public ResponseModel validateRegistrationEndpointRequestParameters(RegistraionModel registraionModel)
			throws IOException {
		if (!StringUtils.isNotBlank(registraionModel.getName())) {
			return getErrorResponse(MessageCode.NAME_NULL_OR_EMPTY);
		}
		if (!StringUtils.isNotBlank(registraionModel.getAlternatePhone())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getConfirmEmail())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getEmail())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getPassword())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getConfirmPassword())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getPhone())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getSourceOfKnowingSite())) {

		}
		if (!StringUtils.isNotBlank(registraionModel.getPreferredTimezone())) {

		}
		return null;
	}

	public ResponseModel getErrorResponse(MessageCode parameter) throws IOException {
		ResponseModel errorResponse = new ResponseModel();
		errorResponse.setCode(String.valueOf(parameter.getKey()));
		errorResponse.setStatus(parameter.getKey());
		errorResponse.setMessage(TaxfilerMessageLoader.getTaxFilerMessageLoaderInstance().getTaxFilerMessageInfo()
				.get(parameter.getValue()));
		httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return errorResponse;
	}

	/**
	 * This is used to create HMAC256 Token using HMACSHA256 algorithm
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NoSuchAlgorithmException
	 */
	public String createHMAC256TokenWithEmptyPayload() throws IllegalArgumentException, NoSuchAlgorithmException {
		String token = null;
		try {
			Key key = generateKey(HMACSHA256);
			Algorithm algorithm = Algorithm.HMAC256(key.toString());
			token = JWT.create().sign(algorithm);
		} catch (JWTCreationException exception) {
			LOGGER.error(exception.getMessage());
		}
		return token;
	}

	/**
	 * This is used to Generate a Key
	 * 
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Key generateKey(String algorithm) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		Key key = keyGen.generateKey();
		return key;
	}

	public static TtlHashMap getTtlhashmap() {
		return ttlHashMap;
	}
	public Object verifySessionId(HttpServletRequest request){
		if (StringUtils.isNotBlank(request.getHeader("sessionId"))){
			if(null == getTtlhashmap().get(request.getHeader("sessionId"))){
				return "invalid sessioId";
			}
			return true;
		}else{
			return "invalid sessioId";
		}
	}

}
