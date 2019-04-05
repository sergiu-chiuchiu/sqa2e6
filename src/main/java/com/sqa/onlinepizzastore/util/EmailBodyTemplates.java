package com.sqa.onlinepizzastore.util;

public class EmailBodyTemplates {
	
	public static String getPasswordResetBodyTemplate(String token, String userName, String reqUrl) {
		String emailBody = "Hi " + userName + "! You are receiving this because you (or someone else) have requested the reset of the password for your PizzaMia account.\n\n" +
				"Please click on the following link, or paste this into your browser to complete the process:\n\n" +
				reqUrl + "/" + token + "/\n\n" +
				"The link can be used only once! " + "\n\n" +
				"If you did not request this, please ignore this email and your password will remain unchanged.\n";
		
		return emailBody;
	}
}
