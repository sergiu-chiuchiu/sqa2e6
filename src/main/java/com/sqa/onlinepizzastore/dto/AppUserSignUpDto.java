package com.sqa.onlinepizzastore.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sqa.onlinepizzastore.validations.ValidPassword;

public class AppUserSignUpDto extends AppUserProfileDto {
	@ValidPassword
	protected String password;
	
	@ValidPassword
	protected String passwordRepeat;

	@Override
	@NotBlank
	@Size(min = 1, max = 1)
	public String getGender() {
		return super.getGender();
	}
	
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
