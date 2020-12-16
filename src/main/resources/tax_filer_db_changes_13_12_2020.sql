ALTER TABLE `users` ADD `auth_code` VARCHAR(45) NULL DEFAULT NULL AFTER `role`;
#ALTER TABLE `dependent_info` ADD `no_of_days_stayed_in_us` INT NOT NULL DEFAULT '0' AFTER `tax_file_year_id`;
ALTER TABLE `dependent_info` MODIFY `no_of_days_stayed_in_us` INT NULL DEFAULT '0';
ALTER TABLE `basic_info` ADD `timezone` VARCHAR(45) NULL DEFAULT NULL AFTER `more_than_one_employer_work_status_comments`;
ALTER TABLE `upload_files` CHANGE `file_type` `file_type` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL;