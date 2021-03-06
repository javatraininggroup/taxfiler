package com.company.taxfiler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.DocsResponseModel;
import com.company.model.EmployeePortalInformationModel;
import com.company.model.EmployeePortalResponseModel;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UploadFilesEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.model.ResponseModel;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.Constants;
import com.company.taxfiler.util.MessageCode;
import com.company.taxfiler.util.TaxfilerUtil;

@RestController
@RequestMapping(Constants.API)
@Transactional
public class EmployeeServicesController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaxfilerUtil taxfilerUtil;
	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping(Constants.GET_USERS_MESSAGES_AND_DOCS_ENDPOINT)
	public Object getUsersMessagesAndDocs(@PathVariable(Constants.USER_ID) int userId,
			@PathVariable(Constants.TAX_YEAR) int taxYear, @PathVariable(Constants.MAIN_STATUS) String mainStatus,
			@PathVariable(Constants.SUB_STATUS) String subStatus/*, @RequestParam("pageSize") int pageSize, @RequestParam("page") int page*/) throws IOException {

		/**
		 * get 1. list of messages 2. list of files are in mainStatus & subStatus
		 */
		
		//Pageable pageable = PageRequest.of(page, pageSize);
		
		//new PageImpl<T>(content, pageable, total)
		EmployeePortalResponseModel employeePortalResponseModel = new EmployeePortalResponseModel();
		Map<String, Long> statusCounters = new HashMap<>();
		List<EmployeePortalInformationModel> employeePortalInfoModelList = new ArrayList<>();
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				List<UserEntity> allCustomersData = null;
				LOGGER.info("mainStatus={}, subStatus={}", mainStatus, subStatus);
				allCustomersData = userRepository.findAll();
				//below code for pagination
				/*long totalPages = userRepository.count(taxYear);
				if(0 != totalPages) {
					totalPages = totalPages/pageSize;
				}
				employeePortalResponseModel.setTotalPages(totalPages);
				Page<UserEntity> pageEntity = userRepository.findAll(taxYear, pageable);
				allCustomersData = pageEntity.toList();*/
				if (!allCustomersData.isEmpty()) {
					for (UserEntity entity : allCustomersData) {
						EmployeePortalInformationModel employeePortalModel = new EmployeePortalInformationModel();
						Set<TaxFiledYearEntity> taxFiledYearEntityList = entity.getTaxFiledYearList();
						if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
							for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
								if (taxFiledYearEntity.getYear() == taxYear
										&& taxFiledYearEntity.getMainStatus().equalsIgnoreCase(mainStatus)
										&& (taxFiledYearEntity.getSubStatus().equalsIgnoreCase(subStatus)
												|| "ALL".equalsIgnoreCase(subStatus))) {

									if (null != statusCounters.get(taxFiledYearEntity.getSubStatus())) {
										statusCounters.put(taxFiledYearEntity.getSubStatus(),
												statusCounters.get(taxFiledYearEntity.getSubStatus()) + 1);
									} else {
										statusCounters.put(taxFiledYearEntity.getSubStatus(), 1l);
									}

									employeePortalModel.setEmail(entity.getEmail());
									employeePortalModel.setUsername(entity.getName());
									employeePortalModel.setId(entity.getId());
									employeePortalModel.setTimezone(entity.getTimezone());

									List<DocsResponseModel> uploadedFilesList = new ArrayList<>();
									/*
									 * List<MessageResponseModel> messagesList = new ArrayList<>();
									 * Set<MessagesEntity> messagesSet = taxFiledYearEntity.getMessagesEntityList();
									 * for (MessagesEntity messagesEntity : messagesSet) { String messageEntityStr =
									 * taxfilerUtil.convertObjectTOString(messagesEntity); MessageResponseModel
									 * messageResponseModelObj = (MessageResponseModel) taxfilerUtil
									 * .convertStringToObject(messageEntityStr, MessageResponseModel.class);
									 * messagesList.add(messageResponseModelObj); }
									 * employeePortalModel.setMessagesList(messagesList);
									 */

									employeePortalModel.setCommentsList(taxFiledYearEntity.getCommentsList());

									Set<UploadFilesEntity> uploadedFilesSet = taxFiledYearEntity
											.getUploadFilesEntityList();
									for (UploadFilesEntity uploadedFilesEntity : uploadedFilesSet) {
										String uploadedFilesEntityStr = taxfilerUtil
												.convertObjectTOString(uploadedFilesEntity);
										DocsResponseModel docsResponseModelObj = (DocsResponseModel) taxfilerUtil
												.convertStringToObject(uploadedFilesEntityStr, DocsResponseModel.class);
										uploadedFilesList.add(docsResponseModelObj);
									}
									employeePortalModel.setUploadedFilesList(uploadedFilesList);
									employeePortalInfoModelList.add(employeePortalModel);
								}
							}
						}
						// employeePortalInfoModelList.add(employeePortalModel);
					}
					if (employeePortalInfoModelList.isEmpty()) {
						return taxfilerUtil.getSuccessResponse("details not available");
					} else {
						employeePortalResponseModel.setEmployeePortalInfoModelList(employeePortalInfoModelList);
						employeePortalResponseModel.setStatusCounters(statusCounters);
						return employeePortalResponseModel;
					}
				} else {
					return taxfilerUtil.getSuccessResponse("details not available");
				}
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
	}
}
