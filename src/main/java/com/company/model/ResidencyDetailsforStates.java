package com.company.model;

import java.util.Set;

public class ResidencyDetailsforStates {

	private int taxYear;
	private Set<TaxYearInfo> taxYearInfoList;

	public int getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public Set<TaxYearInfo> getTaxYearInfoList() {
		return taxYearInfoList;
	}

	public void setTaxYearInfoList(Set<TaxYearInfo> taxYearInfoList) {
		this.taxYearInfoList = taxYearInfoList;
	}

}
