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

@Entity
@Table(name = "upload_files")
public class UploadFilesEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	private TaxFiledYearEntity taxFileYear;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
