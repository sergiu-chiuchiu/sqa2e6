package com.sqa.onlinepizzastore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sqa.onlinepizzastore.validations.ValidPassword;

public class AppUserPassResetDto {
	
	@ValidPassword
	protected String password;

	@ValidPassword
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
