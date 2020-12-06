ALTER TABLE `tax_file_year` ADD `main_status` VARCHAR(100) NOT NULL DEFAULT 'SCHEDULING' AFTER `year`, ADD `sub_status` VARCHAR(100) NOT NULL DEFAULT 'NEW_REGISTER' AFTER `main_status`;

CREATE TABLE `rental_income` (
  `id` int(11) NOT NULL,
  `building_value` double DEFAULT NULL,
  `land_value` double DEFAULT NULL,
  `address_of_property` varchar(150) DEFAULT NULL,
  `date_of_property_purchased` date DEFAULT NULL,
  `property_holder` varchar(45) DEFAULT NULL,
  `income` double DEFAULT NULL,
  `rents_received` double DEFAULT NULL,
  `royalties_received` double DEFAULT NULL,
  `expenses` double DEFAULT NULL,
  `mortgage_interest` double DEFAULT NULL,
  `other_interest` double DEFAULT NULL,
  `insurance` double DEFAULT NULL,
  `repairs` double DEFAULT NULL,
  `auto_and_travel` double DEFAULT NULL,
  `advertising` double DEFAULT NULL,
  `taxes` double DEFAULT NULL,
  `legal_and_other_professional_taxes` double DEFAULT NULL,
  `cleaning_and_maintenance` double DEFAULT NULL,
  `commissions` double DEFAULT NULL,
  `utilities` double DEFAULT NULL,
  `management_fees` double DEFAULT NULL,
  `supplies` double DEFAULT NULL,
  `other_expenses` double DEFAULT NULL,
  `tax_file_year_id` int(11) DEFAULT NULL,
  `no_of_days_rented_during_year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `rental_income` ADD PRIMARY KEY (`id`), ADD KEY `fk_bank_details_tax_file_year10` (`tax_file_year_id`);
  
ALTER TABLE `rental_income` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
  
ALTER TABLE `rental_income` ADD CONSTRAINT `fk_bank_details_tax_file_year10` FOREIGN KEY (`tax_file_year_id`) REFERENCES `tax_file_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;