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
