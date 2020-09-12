package com.company.taxfiler;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationWebConfig {

	@Bean
	public Filter simpleCORSFilter() {
		return new SimpleCORSFilter();
	}
}
