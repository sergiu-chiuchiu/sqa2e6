package com.sqa.onlinepizzastore.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class EncryptedPasswordUtils {

	// Encrypt Password with BCryptPasswordEncoder
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
}
