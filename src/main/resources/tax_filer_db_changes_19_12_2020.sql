--
-- Database: `tax_filer`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `tax_file_year_id` int(11) DEFAULT 0,
  `last_updated_date` varchar(30) DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_messages_tax_file_year1_idx` (`tax_file_year_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `fk_messages_tax_file_year10` FOREIGN KEY (`tax_file_year_id`) REFERENCES `tax_file_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
