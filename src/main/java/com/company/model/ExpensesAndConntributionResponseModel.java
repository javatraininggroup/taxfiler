package com.company.model;

import java.util.Set;

import com.company.taxfiler.dao.ContributionEntity;
import com.company.taxfiler.dao.ExpensesEntity;

public class ExpensesAndConntributionResponseModel {

	private Set<ExpensesEntity> expensesInfoList;

	private Set<ContributionEntity> contributionInfoList;

	public Set<ExpensesEntity> getExpensesInfoList() {
		return expensesInfoList;
	}

	public void setExpensesInfoList(Set<ExpensesEntity> expensesInfoList) {
		this.expensesInfoList = expensesInfoList;
	}

	public Set<ContributionEntity> getContributionInfoList() {
		return contributionInfoList;
	}

	public void setContributionInfoList(Set<ContributionEntity> contributionInfoList) {
		this.contributionInfoList = contributionInfoList;
	}

}
