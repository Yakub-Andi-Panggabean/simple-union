package com.polsri.union.app.util.credential;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CredentialsInspector {

	public static final String generatePassword(String password) throws Exception {
		return new BCryptPasswordEncoder().encode(password);
	}

	public static final boolean isPasswordMacthes(String password, String hashedPassword) throws Exception {
		return new BCryptPasswordEncoder().matches(password, hashedPassword);
	}
}
