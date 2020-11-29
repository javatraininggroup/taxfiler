package com.company.taxfiler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.DocsResponseModel;
import com.company.model.EmployeePortalInformationModel;
import com.company.model.MessageResponseModel;
import com.company.taxfiler.dao.MessagesEntity;
import com.company.taxfiler.dao.TaxFiledYearEntity;
import com.company.taxfiler.dao.UploadFilesEntity;
import com.company.taxfiler.dao.UserEntity;
import com.company.taxfiler.repository.UserRepository;
import com.company.taxfiler.util.TaxfilerUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class EmployeeServicesController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaxfilerUtil taxfilerUtil;
	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping("/users/{user_id}/taxYear/{tax_year}/mainStatus/{mainStatus}/subStatus/{subStatus}/customersInfo")
	public Object getUsersMessagesAndDocs(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear,
			@PathVariable("mainStatus") String mainStatus, @PathVariable("subStatus") String subStatus) {

		/**
		 * get 1. list of messages 2. list of files are in mainStatus & subStatus
		 */

		List<EmployeePortalInformationModel> employeePortalInfoModelList = new ArrayList<>();
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof String)
			return verifySessionIdResponse;

		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				List<UserEntity> allCustomersData = null;
				LOGGER.info("mainStatus={}, subStatus={}", mainStatus, subStatus);
				allCustomersData = userRepository.findAll();
				if (!allCustomersData.isEmpty()) {
					for (UserEntity entity : allCustomersData) {
						EmployeePortalInformationModel employeePortalModel = new EmployeePortalInformationModel();
						employeePortalModel.setEmail(entity.getEmail());
						employeePortalModel.setUsername(entity.getName());
						employeePortalModel.setId(entity.getId());
						employeePortalModel.setTimezone(entity.getTimezone());
						Set<TaxFiledYearEntity> taxFiledYearEntityList = entity.getTaxFiledYearList();
						if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
							for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
								if (taxFiledYearEntity.getYear() == taxYear) {
									List<MessageResponseModel> messagesList = new ArrayList<>();
									List<DocsResponseModel> uploadedFilesList = new ArrayList<>();
									Set<MessagesEntity> messagesSet = taxFiledYearEntity.getMessagesEntityList();
									for (MessagesEntity messagesEntity : messagesSet) {
										boolean isCheckSuccess = false;
										if (subStatus.equalsIgnoreCase("ALL")) {
											if (messagesEntity.getMainStatus().equalsIgnoreCase(mainStatus)) {
												isCheckSuccess = true;
											}
										} else {
											if (messagesEntity.getMainStatus().equalsIgnoreCase(mainStatus)
													&& messagesEntity.getSubStatus().equalsIgnoreCase(subStatus)) {
												isCheckSuccess = true;
											}
										}
										if (isCheckSuccess) {
											String messageEntityStr = taxfilerUtil
													.convertObjectTOString(messagesEntity);
											MessageResponseModel messageResponseModelObj = (MessageResponseModel) taxfilerUtil
													.convertStringToObject(messageEntityStr,
															MessageResponseModel.class);
											messagesList.add(messageResponseModelObj);
										}
									}
									employeePortalModel.setMessagesList(messagesList);

									Set<UploadFilesEntity> uploadedFilesSet = taxFiledYearEntity
											.getUploadFilesEntityList();
									for (UploadFilesEntity uploadedFilesEntity : uploadedFilesSet) {
										boolean isCheckSuccess = false;
										if (subStatus.equalsIgnoreCase("ALL")) {
											if (uploadedFilesEntity.getMainStatus().equalsIgnoreCase(mainStatus)) {
												isCheckSuccess = true;
											}
										} else {
											if (uploadedFilesEntity.getMainStatus().equalsIgnoreCase(mainStatus)
													&& uploadedFilesEntity.getSubStatus().equalsIgnoreCase(subStatus)) {
												isCheckSuccess = true;
											}
										}
										if (isCheckSuccess) {
											String uploadedFilesEntityStr = taxfilerUtil
													.convertObjectTOString(uploadedFilesEntity);
											DocsResponseModel docsResponseModelObj = (DocsResponseModel) taxfilerUtil
													.convertStringToObject(uploadedFilesEntityStr,
															DocsResponseModel.class);
											uploadedFilesList.add(docsResponseModelObj);
										}
									}
									employeePortalModel.setUploadedFilesList(uploadedFilesList);
								}
							}
						}
						employeePortalInfoModelList.add(employeePortalModel);
					}
					return employeePortalInfoModelList;
				} else {
					return "no customers data available";
				}
			} else {
				return "userId not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "an error has occured";
		}
	}
}
