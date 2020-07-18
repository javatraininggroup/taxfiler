package com.company.model;

import java.util.List;

public class ResidencyDetailsforStates {

	private int taxYear;
	private List<TaxYearInfo> taxYearInfoList;

	public int getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public List<TaxYearInfo> getTaxYearInfoList() {
		return taxYearInfoList;
	}

	public void setTaxYearInfoList(List<TaxYearInfo> taxYearInfoList) {
		this.taxYearInfoList = taxYearInfoList;
	}

}
