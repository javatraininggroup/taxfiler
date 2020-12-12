package com.company.model;

import java.util.Set;

public class ExpensesAndContributionModel {

	private Set<OtherIncomeInfoData> expensesInfoList;

	private Set<OtherIncomeInfoData> contributionInfoList;

	public Set<OtherIncomeInfoData> getExpensesInfoList() {
		return expensesInfoList;
	}

	public void setExpensesInfoList(Set<OtherIncomeInfoData> expensesInfoList) {
		this.expensesInfoList = expensesInfoList;
	}

	public Set<OtherIncomeInfoData> getContributionInfoList() {
		return contributionInfoList;
	}

	public void setContributionInfoList(Set<OtherIncomeInfoData> contributionInfoList) {
		this.contributionInfoList = contributionInfoList;
	}

}
