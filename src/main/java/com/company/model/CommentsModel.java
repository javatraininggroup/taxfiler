package com.company.model;

import java.util.Date;

public class CommentsModel {

	private String comment;
	private String lastUpdatedBy;
	private /*Date*/ String lastUpdatedDate;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public /*Date*/String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(/*Date*/String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
