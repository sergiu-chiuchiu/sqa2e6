package com.sqa.onlinepizzastore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AppUserPassResetDto {
	
	@NotBlank
	@Size(min = 8, max = 40)
	protected String password;
	
	@NotBlank
	@Size(min = 8, max = 40)
	protected String passwordRepeat;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	
}
