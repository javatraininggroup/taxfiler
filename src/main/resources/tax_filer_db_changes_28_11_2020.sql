ALTER TABLE `basic_info` modify `ssn` varchar(20);

ALTER TABLE `other_income_informaton` CHANGE `question` `question` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL;

ALTER TABLE `additional_information` CHANGE `question` `question` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL; 

ALTER TABLE `contact_details` CHANGE `address` `address` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL; 

CREATE TABLE `day_care` (
  `id` int(11) NOT NULL,
  `dependent_info_id` int(11) NOT NULL,
  `inst_name` varchar(100) DEFAULT NULL,
  `inst_tax_id` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `door_no` varchar(45) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `day_care` ADD PRIMARY KEY (`id`), ADD KEY `fk_day_care_dependent_info1_idx` (`dependent_info_id`);
  
ALTER TABLE `day_care` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
  
ALTER TABLE `day_care` ADD CONSTRAINT `fk_day_care_dependent_info1` FOREIGN KEY (`dependent_info_id`) REFERENCES `dependent_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;