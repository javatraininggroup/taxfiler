package com.company.taxfiler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.model.DownloadModel;
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
public class UploadDownloadDocsController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaxfilerUtil taxfilerUtil;
	@Autowired
	private HttpServletRequest httpServletRequest;
	// private final static String DEFAULT_MAIN_STATUS = "SCHEDULING";
	// private final static String DEFAULT_SUB_STATUS = "PENDING";

	@PostMapping(Constants.POST_UPLOAD_DOCS_ENDPOINT)
	public Object uploadDocs(@RequestParam(Constants.FILE_NAME) String fileName,
			@RequestParam(Constants.FILE_TYPE) String fileType, @RequestParam(Constants.COMMENT) String comment,
			@PathVariable(Constants.USER_ID) int userId, @PathVariable(Constants.TAX_YEAR) int taxYear,
			@RequestParam(Constants.FILE) MultipartFile file) throws Exception {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		boolean isTaxYearAvailable = false;
		if (!file.isEmpty()) {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<UploadFilesEntity> uploadFilesEntitySet = taxFiledYearEntity.getUploadFilesEntityList();
							if (null == uploadFilesEntitySet) {
								uploadFilesEntitySet = new HashSet<>();
								taxFiledYearEntity.setUploadFilesEntityList(uploadFilesEntitySet);
							}
							UploadFilesEntity uploadFilesEntity = new UploadFilesEntity();
							uploadFilesEntity.setComments(comment);
							byte[] bytes = file.getBytes();
							uploadFilesEntity.setFileContent(bytes);
							uploadFilesEntity.setFileName(fileName);
							uploadFilesEntity.setFileType(file.getContentType());
							uploadFilesEntity.setReqFileType(fileType);
							uploadFilesEntity.setYear(taxYear);
							uploadFilesEntity.setDate(new Date().toString());
							uploadFilesEntity.setDownloadId(String.valueOf(System.nanoTime()));
							uploadFilesEntity.setTaxFileYear(taxFiledYearEntity);
							// uploadFilesEntity.setMainStatus(DEFAULT_MAIN_STATUS);
							// uploadFilesEntity.setSubStatus(DEFAULT_SUB_STATUS);
							uploadFilesEntitySet.add(uploadFilesEntity);
							isTaxYearAvailable = true;
							return prepareFilesDetailsForDownload(uploadFilesEntitySet);
						}
					}
				}
				if (!isTaxYearAvailable) {
					LOGGER.info("inserting into db as no records found");

					if (null == taxFiledYearEntityList) {
						taxFiledYearEntityList = new HashSet<>();
					}
					TaxFiledYearEntity taxFiledYearEntity = new TaxFiledYearEntity();
					taxFiledYearEntity.setYear(taxYear);

					Set<UploadFilesEntity> uploadFilesEntitySet = new HashSet<>();
					taxFiledYearEntity.setUploadFilesEntityList(uploadFilesEntitySet);

					UploadFilesEntity uploadFilesEntity = new UploadFilesEntity();
					uploadFilesEntity.setComments(comment);
					byte[] bytes = file.getBytes();
					uploadFilesEntity.setFileContent(bytes);
					uploadFilesEntity.setFileName(fileName);
					uploadFilesEntity.setFileType(file.getContentType());
					uploadFilesEntity.setReqFileType(fileType);
					uploadFilesEntity.setYear(taxYear);
					uploadFilesEntity.setDate(new Date().toString());
					uploadFilesEntity.setDownloadId(String.valueOf(System.nanoTime()));
					uploadFilesEntity.setTaxFileYear(taxFiledYearEntity);
					// uploadFilesEntity.setMainStatus(DEFAULT_MAIN_STATUS);
					// uploadFilesEntity.setSubStatus(DEFAULT_SUB_STATUS);
					uploadFilesEntitySet.add(uploadFilesEntity);
					taxFiledYearEntityList.add(taxFiledYearEntity);
					taxFiledYearEntity.getUploadFilesEntityList().addAll(uploadFilesEntitySet);
					taxFiledYearEntity.setUserEntity(userEntity);
					userEntity.setTaxFiledYearList(taxFiledYearEntityList);
					userRepository.save(userEntity);
					return prepareFilesDetailsForDownload(uploadFilesEntitySet);
				}

			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}

		} else {
			return taxfilerUtil.getErrorResponse(MessageCode.FAILED_TO_UPLOAD_BECAUSE_FILE_WAS_EMPTY);
		}
		return taxfilerUtil.getSuccessResponse(Constants.SUCCESS);
	}

	@GetMapping(Constants.GET_DOWNLOAD_FILE_ENDPOINT)
	public Object downloadFile(@PathVariable(Constants.FILE_ID) String fileId,
			@PathVariable(Constants.USER_ID) int userId, @PathVariable(Constants.TAX_YEAR) int taxYear)
			throws Exception {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<UploadFilesEntity> uploadFilesEntitySet = taxFiledYearEntity.getUploadFilesEntityList();
							for (UploadFilesEntity uploadFilesEntity : uploadFilesEntitySet) {
								if (uploadFilesEntity.getDownloadId().equals(fileId)) {
									return ResponseEntity.ok()
											.contentType(MediaType.parseMediaType(uploadFilesEntity.getFileType()))
											.header(HttpHeaders.CONTENT_DISPOSITION,
													"attachment; filename=\"" + uploadFilesEntity.getFileName() + "\"")
											.body(new ByteArrayResource(uploadFilesEntity.getFileContent()));
								}
							}

						}
					}
				}
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
		return taxfilerUtil.getErrorResponse(MessageCode.FILE_NOT_AVAILABLE_TO_DOWNLOAD);
	}

	@GetMapping(Constants.GET_ALL_UPLOAD_DOCS_ENDPOINT)
	public Object getAllUploadDocs(@PathVariable(Constants.USER_ID) int userId,
			@PathVariable(Constants.TAX_YEAR) int taxYear) throws IOException {
		Object verifySessionIdResponse = taxfilerUtil.verifySessionId(httpServletRequest);
		if (verifySessionIdResponse instanceof ResponseModel)
			return verifySessionIdResponse;
		try {
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
			if (optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Set<TaxFiledYearEntity> taxFiledYearEntityList = userEntity.getTaxFiledYearList();
				if (null != taxFiledYearEntityList && taxFiledYearEntityList.size() > 0) {
					for (TaxFiledYearEntity taxFiledYearEntity : taxFiledYearEntityList) {
						if (taxFiledYearEntity.getYear() == taxYear) {
							Set<UploadFilesEntity> uploadFilesEntitySet = taxFiledYearEntity.getUploadFilesEntityList();
							return prepareFilesDetailsForDownload(uploadFilesEntitySet);

						}
					}
				}
			} else {
				return taxfilerUtil.getErrorResponse(MessageCode.USER_NOT_REGISTERED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return taxfilerUtil.getErrorResponse(MessageCode.AN_ERROR_HAS_OCCURED, e.getMessage());
		}
		return taxfilerUtil.getSuccessResponse("details not available");

	}

	public List<DownloadModel> prepareFilesDetailsForDownload(Set<UploadFilesEntity> uploadFilesEntitySet)
			throws Exception {
		List<DownloadModel> downloadFileModelList = new ArrayList<>();
		try {
			for (UploadFilesEntity uploadFilesEntity : uploadFilesEntitySet) {
				DownloadModel downloadModel = new DownloadModel();
				downloadModel.setDate(uploadFilesEntity.getDate());
				downloadModel.setDownloadLink(uploadFilesEntity.getDownloadId());
				downloadModel.setFileName(uploadFilesEntity.getFileName());
				downloadModel.setFileType(uploadFilesEntity.getReqFileType());
				downloadFileModelList.add(downloadModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadFileModelList;
	}

}
