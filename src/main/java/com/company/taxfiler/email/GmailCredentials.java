package com.company.taxfiler.email;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class GmailCredentials {
	private String userEmail;
	private String clientId;
	private String clientSecret;
	private String accessToken;
	private String refreshToken;
}
