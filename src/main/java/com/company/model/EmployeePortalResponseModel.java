package com.company.model;

import java.util.List;
import java.util.Map;

public class EmployeePortalResponseModel {

	private List<EmployeePortalInformationModel> employeePortalInfoModelList;
	private Map<String, Long> statusCounters;
	private long totalPages;

	public List<EmployeePortalInformationModel> getEmployeePortalInfoModelList() {
		return employeePortalInfoModelList;
	}

	public void setEmployeePortalInfoModelList(List<EmployeePortalInformationModel> employeePortalInfoModelList) {
		this.employeePortalInfoModelList = employeePortalInfoModelList;
	}

	public Map<String, Long> getStatusCounters() {
		return statusCounters;
	}

	public void setStatusCounters(Map<String, Long> statusCounters) {
		this.statusCounters = statusCounters;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

}
