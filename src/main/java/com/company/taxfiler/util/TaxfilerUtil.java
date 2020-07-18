package com.company.taxfiler.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.company.taxfiler.model.RegistraionModel;
import com.company.taxfiler.model.ResponseModel;

import ch.qos.logback.core.status.Status;

@Component
public class TaxfilerUtil {

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

}
