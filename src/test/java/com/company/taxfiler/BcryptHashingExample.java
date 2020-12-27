package com.company.taxfiler;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptHashingExample {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String originalPassword = "123456";
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
		System.out.println(generatedSecuredPasswordHash);

		boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
		System.out.println(matched);
		
		
		generatedSecuredPasswordHash = "$2a$12$R9s5/ZpFcS5tuZOGUNZTiuk0O361y9xn5rGm4rLkfTRQ7tDFJAboe";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!passwordEncoder.matches("ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413", generatedSecuredPasswordHash)) {
			System.out.println("password matched");
		}else {
			System.out.println("Not matched");
		}
		
		System.out.println("year: "+Calendar.getInstance().get(Calendar.YEAR));
	}
}
