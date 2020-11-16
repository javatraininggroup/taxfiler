package com.company.taxfiler.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "messages")
public class MessagesEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_file_year_id", referencedColumnName = "id")
	@JsonManagedReference
	private TaxFiledYearEntity taxFileYear;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String subject;
	private String message;
	private Date date;
	@Column(name = "main_status")
	private String mainStatus;
	@Column(name = "sub_status")
	private String subStatus;
	@Column(name = "is_sent_message")
	private boolean isSentMessage;
	@Column(name = "is_received_message")
	private boolean isReceivedMessage;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public boolean isSentMessage() {
		return isSentMessage;
	}

	public void setSentMessage(boolean isSentMessage) {
		this.isSentMessage = isSentMessage;
	}

	public boolean isReceivedMessage() {
		return isReceivedMessage;
	}

	public void setReceivedMessage(boolean isReceivedMessage) {
		this.isReceivedMessage = isReceivedMessage;
	}

}
