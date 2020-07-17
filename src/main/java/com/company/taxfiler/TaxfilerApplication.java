package com.company.taxfiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxfilerApplication {

	public static void main(String[] args) {
		System.out.println("Entering into main");
		SpringApplication.run(TaxfilerApplication.class, args);
	}

}
