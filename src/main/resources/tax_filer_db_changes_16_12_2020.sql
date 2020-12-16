alter table bank_details modify tax_file_year_id int(11) default 0;

alter table basic_info modify tax_file_year_id int(11) default 0;

alter table contact_details modify tax_file_year_id int(11) default 0;

alter table contact_details modify zip int(11) default 0;

alter table contribution modify tax_file_year_id int(11) default 0;

alter table day_care modify dependent_info_id int(11) default 0;

alter table dependent_info modify no_of_days_stayed_in_us int(11) default 0;

alter table dependent_info modify tax_file_year_id int(11) default 0;

alter table dependent_info modify check_if_itin_to_be_applied tinyint(1) default 0;

 alter table dependent_info modify check_if_ITIN_to_be_renewed tinyint(1) default 0;
 
 alter table dependent_info modify itin_renewed tinyint(1) default 0;
 
 alter table dependent_info modify lived_for_more_than_06_months tinyint(1) default 0;
 
 alter table dependent_info modify provided_more_than_50PE_support tinyint(1) default 0;
 
 alter table dependent_info modify is_you_and_spouse_working tinyint(1) default 0;
 
 alter table expenses modify tax_file_year_id int(11) default 0;
 
 alter table fbar modify tax_file_year_id int(11) default 0;
 
 alter table fbar modify pincode int(11) default 0;
 
 alter table messages modify tax_file_year_id int(11) default 0;
 
 alter table messages modify is_sent_message tinyint(1) default 0;
 
 alter table messages modify is_received_message tinyint(1) default 0;
 
 alter table other_income_informaton modify tax_file_year_id int(11) default 0;
 
 alter table other_information modify tax_file_year_id int(11) default 0;
 
 alter table rental_income modify tax_file_year_id int(11) default 0;
 
 alter table rental_income modify no_of_days_rented_during_year int(11) default 0;
 
 alter table rental_income modify other_expenses double default 0;
 alter table rental_income modify supplies double default 0;
 alter table rental_income modify management_fees double default 0;
 alter table rental_income modify utilities double default 0;
 alter table rental_income modify commissions double default 0;
 alter table rental_income modify cleaning_and_maintenance double default 0;
 alter table rental_income modify legal_and_other_professional_taxes double default 0;
 alter table rental_income modify taxes double default 0;
 alter table rental_income modify advertising double default 0;
 alter table rental_income modify auto_and_travel double default 0;
 alter table rental_income modify repairs double default 0;
 alter table rental_income modify insurance double default 0;
 alter table rental_income modify other_interest double default 0;
 alter table rental_income modify mortgage_interest double default 0;
 alter table rental_income modify expenses double default 0;
 alter table rental_income modify royalties_received double default 0;
 alter table rental_income modify rents_received double default 0;
 alter table rental_income modify income double default 0;
 alter table rental_income modify land_value double default 0;
 alter table rental_income modify building_value double default 0;
 
 alter table residency_details_for_states modify tax_file_year_id int(11) default 0;
 alter table residency_details_for_states modify tax_year int(11) default 0;
 alter table residency_details_for_states modify is_this_spouse tinyint(1) default 0;
 
 
 alter table tax_file_year modify users_id int(11) default 0;
 alter table tax_file_year modify year int(11) default 0;
 
 alter table upload_files modify tax_file_year_id int(11) default 0;
 alter table upload_files modify year int(11) default 0;
 
 alter table users modify confirm_details tinyint(1) default 0;