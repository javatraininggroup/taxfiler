package com.company.taxfiler.email;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

public class Main {

	public static void main(String[] args) {
		try {
			GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
			/*GmailCredentials GmailCredentials = new GmailCredentials("rajavijayakanth.b@gmail.com",
					"916449471704-tpgvac5kit66mqu64sbfahd2bp6ob6i8.apps.googleusercontent.com",
					"KCQNp6mXGOi966NNZ-ANwWm7",
					"ya29.a0AfH6SMDdVx2LTuCqtLbYWmEJyoaZX1Wae4WAZahtsQ1ok3CCtYS9UJitpVHUXvP00F6L45_KpkwW5AwMzIfGQr6BBoXDFpaZbtKrwFuyqT19d4KuQjSezeTJM3U3H4yYsH3HoMq738HlS9aJfbEa729H-pYdqDQgMsEeUsovhRg",
					"1//04skwY42xuWh2CgYIARAAGAQSNwF-L9IrpeZ_exIf5nr6HiEAGdJoBtocQ7E4nG8Y8p1BaR1bV5tZc6LD0wnMLHLiSjUa9goO6h4");

			gmailService.setGmailCredentials(GmailCredentials);*/
			
			gmailService.setGmailCredentials(GmailCredentials.builder()
                    .userEmail("YOUR_EMAIL@gmail.com")
                    .clientId("1078329436949-rspqf1hkbedrqavvcakn8k6l6qd9asnl.apps.googleusercontent.com")
                    .clientSecret("7Axm_PMdL3lppLdkA-MmPo7h")
                    .accessToken("ya29.GluCBY6YE-TzEU2-F86sRl_Gq5QyPmUNW2wEV0MynFN-L3HK2AHEUD09pknXfrvk8UY6NYnGwuCIxAh97s6ipVylgwoNIsxLs7uouIBqj8vWiAODGiS2a1ZDNa8D")
                    .refreshToken("1/XyMnZb4UfU8WDt6SnHIeE3wFTPyTAg2K16ZA7NIF0bY")
                    .build());

			gmailService.sendMessage("rajavijayakanth@gmail.com", "Subject", "body text");
		} catch (GeneralSecurityException | IOException | MessagingException e) {
			e.printStackTrace();
		}
	}

}
