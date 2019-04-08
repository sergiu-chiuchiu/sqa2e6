package com.sqa.onlinepizzastore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AppUserEmailDto {
	@Email
	@NotBlank
	@Size(min = 4, max = 40)
	protected String email;

	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
