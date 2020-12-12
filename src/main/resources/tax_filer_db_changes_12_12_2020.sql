ALTER TABLE `tax_file_year` CHANGE `main_status` `main_status` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SCHEDULING', CHANGE `sub_status` `sub_status` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'NEW_REGISTER';

CREATE TABLE `contribution` (
  `id` int(11) NOT NULL,
  `question` varchar(200) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `tax_file_year_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `contribution`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_other_income_informaton_tax_file_year1_idx` (`tax_file_year_id`);
  
ALTER TABLE `contribution`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
  
ALTER TABLE `contribution`
  ADD CONSTRAINT `fk_other_income_informaton_tax_file_year100` FOREIGN KEY (`tax_file_year_id`) REFERENCES `tax_file_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
  
CREATE TABLE `expenses` (
  `id` int(11) NOT NULL,
  `question` varchar(200) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `tax_file_year_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `expenses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_other_income_informaton_tax_file_year1_idx` (`tax_file_year_id`);
  
ALTER TABLE `expenses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
  
ALTER TABLE `expenses`
  ADD CONSTRAINT `fk_other_income_informaton_tax_file_year10` FOREIGN KEY (`tax_file_year_id`) REFERENCES `tax_file_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `basic_info` ADD `did_you_work_more_than_one_employer_in_XX` TINYINT(1) NULL DEFAULT NULL AFTER `citizenship`, ADD `more_than_one_employer_work_status_comments` VARCHAR(200) NULL DEFAULT NULL AFTER `did_you_work_more_than_one_employer_in_XX`;

ALTER TABLE `basic_info` CHANGE `did_you_work_more_than_one_employer_in_XX` `did_you_work_more_than_one_employer_in_XX` TINYINT(1) NULL DEFAULT '0';

ALTER TABLE `spouse_details` CHANGE `check_if_ITIN_to_be_applied` `check_if_ITIN_to_be_applied` TINYINT(1) NULL DEFAULT '0', CHANGE `check_if_ITIN_to_be_renewed` `check_if_ITIN_to_be_renewed` TINYINT(1) NULL DEFAULT '0', CHANGE `itin_renewed` `itin_renewed` TINYINT(1) NULL DEFAULT '0', CHANGE `did_your_spouse_is_worked_in_XX` `did_your_spouse_is_worked_in_XX` TINYINT(1) NULL DEFAULT '0', CHANGE `is_living_more_than_6months` `is_living_more_than_6months` TINYINT(1) NULL DEFAULT '0', CHANGE `did_you_work_more_than_one_employer_in_XX` `did_you_work_more_than_one_employer_in_XX` TINYINT(1) NULL DEFAULT '0';

ALTER TABLE `bank_details` CHANGE `routing_number` `routing_number` BIGINT NULL DEFAULT NULL, CHANGE `bank_account_number` `bank_account_number` BIGINT NULL DEFAULT NULL;

ALTER TABLE `bank_details` CHANGE `routing_number` `routing_number` BIGINT(20) NULL DEFAULT '0', CHANGE `bank_account_number` `bank_account_number` BIGINT(20) NULL DEFAULT '0';

ALTER TABLE `spouse_details` ADD `type_of_visa` VARCHAR(45) NULL AFTER `more_than_one_employer_work_status_comments`;