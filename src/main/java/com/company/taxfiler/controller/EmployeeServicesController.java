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
import com.google.gson.Gson;

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
	private final static String DEFAULT_MAIN_STATUS = "SCHEDULING";
	private Gson gson = new Gson();

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
				List<UserEntity> allCustomersData = userRepository.findAll();
				if (!allCustomersData.isEmpty()) {
					//System.out.println("data: " + gson.toJson(allCustomersData));
					for (UserEntity entity : allCustomersData) {
						EmployeePortalInformationModel employeePortalModel = new EmployeePortalInformationModel();
						employeePortalModel.setEmail(entity.getEmail());
						employeePortalModel.setUsername(entity.getName());
						Set<TaxFiledYearEntity> taxFiledYearEntityList = entity.getTaxFiledYearList();
						if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
							for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
								if (taxFiledYearEntity.getYear() == taxYear) {
									List<MessageResponseModel> messagesList = new ArrayList<>();
									List<DocsResponseModel> uploadedFilesList = new ArrayList<>();
									//List<UploadFilesEntity> uploadedFilesList = new ArrayList<>();
									Set<MessagesEntity> messagesSet = taxFiledYearEntity.getMessagesEntityList();
									for (MessagesEntity messagesEntity : messagesSet) {
										if (messagesEntity.getMainStatus().equalsIgnoreCase(mainStatus)) {
											String messageEntityStr = taxfilerUtil.convertObjectTOString(messagesEntity);
											MessageResponseModel messageResponseModelObj = (MessageResponseModel)taxfilerUtil.convertStringToObject(messageEntityStr, MessageResponseModel.class);
											messagesList.add(messageResponseModelObj);
										}
									}
									employeePortalModel.setMessagesList(messagesList);
									
									Set<UploadFilesEntity> uploadedFilesSet = taxFiledYearEntity
											.getUploadFilesEntityList();
									for (UploadFilesEntity uploadedFilesEntity : uploadedFilesSet) {
										if (uploadedFilesEntity.getMainStatus().equalsIgnoreCase(mainStatus)) {
											String uploadedFilesEntityStr = taxfilerUtil.convertObjectTOString(uploadedFilesEntity);
											DocsResponseModel docsResponseModelObj = (DocsResponseModel)taxfilerUtil.convertStringToObject(uploadedFilesEntityStr, DocsResponseModel.class);
											/*DocsResponseModel docsResponseModelObj = new DocsResponseModel();
											docsResponseModelObj.setComment(uploadedFilesEntity.getComments());
											docsResponseModelObj.setDate(uploadedFilesEntity.getDate());
											docsResponseModelObj.setDownloadLink(uploadedFilesEntity.getDownloadId());
											docsResponseModelObj.setFileName(uploadedFilesEntity.getFileName());
											docsResponseModelObj.setFileType(uploadedFilesEntity.getFileType());*/
											uploadedFilesList.add(docsResponseModelObj);
											//uploadedFilesList.add(uploadedFilesEntity);
										}
									}
									employeePortalModel.setUploadedFilesList(uploadedFilesList);
								}
							}
						}
						employeePortalInfoModelList.add(employeePortalModel);
					}
					//System.out.println("employeePortalInfoModelList: " + employeePortalInfoModelList);
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
