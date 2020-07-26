package com.company.taxfiler.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import com.company.taxfiler.repository.UserRepository;

@RestController
@RequestMapping("/api")
@Transactional
public class UploadDownloadDocsController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/upload/{user_id}/{tax_year}")
	public Object uploadDocs(@RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType,
			@RequestParam("comment") String comment, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear, @RequestParam("file") MultipartFile file) throws Exception {
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
							uploadFilesEntitySet.add(uploadFilesEntity);
							uploadFilesEntity.setTaxFileYear(taxFiledYearEntity);
							return prepareFilesDetailsForDownload(uploadFilesEntitySet);
						}
					}
				}
			} else {
				return "user not found";
			}

		} else {
			return "You failed to upload " + fileName + " because the file was empty.";
		}
		return "success";
	}

	@GetMapping("/download/{user_id}/{tax_year}/{fileId}")
	public Object downloadFile(@PathVariable("fileId") String fileId, @PathVariable("user_id") int userId,
			@PathVariable("tax_year") int taxYear) throws Exception {
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
				} else {
					throw new Exception("please select tax year to proceed");
				}
			} else {
				throw new Exception("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "file not available";
	}

	@GetMapping("/docs/{user_id}/{tax_year}")
	public Object getAllUploadDocs(@PathVariable("user_id") int userId, @PathVariable("tax_year") int taxYear) {
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
				} else {
					throw new Exception("please select tax year to proceed");
				}
			} else {
				throw new Exception("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

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
			throw new Exception("an error has occured");
		}
		return downloadFileModelList;
	}

}
