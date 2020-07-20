package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity	
@Table(name="other_informtion")
public class OtherInformation {

	
	@Column(name="please_update_if_any_other_information")
	
	private String pleaseUpdateIfAnyOtherInformation;

	public String getPleaseUpdateIfAnyOtherInformation() {
		return pleaseUpdateIfAnyOtherInformation;
	}

	public void setPleaseUpdateIfAnyOtherInformation(String pleaseUpdateIfAnyOtherInformation) {
		this.pleaseUpdateIfAnyOtherInformation = pleaseUpdateIfAnyOtherInformation;
	}
}
