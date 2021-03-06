package com.company.taxfiler.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "upload_files")
public class UploadFilesEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	@JsonBackReference
	private TaxFiledYearEntity taxFileYear;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private long year;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "file_type")
	private String fileType;
	private String comments;
	@Column(name = "file_content")
	@Lob
	private byte[] fileContent;
	private String date;
	@Column(name = "download_id")
	private String downloadId;
	@Column(name = "req_file_type")
	private String reqFileType;
	@Column(name = "main_status")
	private String mainStatus;
	@Column(name = "sub_status")
	private String subStatus;

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public TaxFiledYearEntity getTaxFileYear() {
		return taxFileYear;
	}

	public void setTaxFileYear(TaxFiledYearEntity taxFileYear) {
		this.taxFileYear = taxFileYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}

	public String getReqFileType() {
		return reqFileType;
	}

	public void setReqFileType(String reqFileType) {
		this.reqFileType = reqFileType;
	}

	public String getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

}
