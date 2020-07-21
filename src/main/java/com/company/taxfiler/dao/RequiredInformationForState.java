package com.company.taxfiler.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="required_Information_For_State")
public class RequiredInformationForState {

	private String id;
	
	@Column(name = "tax_filed_year_id")
	private long taxFiledYearId;
	
	@Column(name="if_you_are_resident_of_MA_State?_Provide_Per_month_rent_you_are_paying")
	private boolean maStateRentPaying;
	
	@Column(name="if_you_are_resident_of_MA_State?_Are_you_and_your_family_covered_with_MA_health_insurance?_If_yes_mention_the_no_of_months_covered")
	private boolean maStateFamilyCoveredUnderInsurance;
	
	@Column(name="if_you_are_resident_of_NJ_State?_Provide_Per_month_rent_you_are_paying")
	private boolean njStateRentYouArePaying;
	
	@Column(name="if_you_are_resident_of_IN_State?_Provide_Per_month_rent_you_are_paying")
	private boolean inStateRentYouArePaying;
	
	@Column(name="if_you_are_resident_of_WI_State?_Provide_Per_month_rent_you_are_paying")
	private boolean wiStateRentYouArePaying;

	@Column(name="if_you_are_paid_any_education_saving_plan _529_plan_If_yes_please_provide_the_document")
	private boolean paidAnyEducation529Plan;
	
	@Column(name="did_you_file_for_Iowa_and_Oregon_last_year_2018?_If_yes_upload_the_whole_Tax_Returns")
	private boolean fileForIowaAndOregon;
	
	@Column(name="if_you_are_resident_of_OH_&_PA_for_2019?_If_yes_please_provide_your_Home_and_Work_address")
	private boolean residentOfOhAndPaFor2019;
	
	private String comment;
}
