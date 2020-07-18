package com.company.model;

import java.util.Date;

public class TaxYearInfo {

	private String stateResided;
	private Date startDate;
	private Date endDate;

	public String getStateResided() {
		return stateResided;
	}

	public void setStateResided(String stateResided) {
		this.stateResided = stateResided;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
