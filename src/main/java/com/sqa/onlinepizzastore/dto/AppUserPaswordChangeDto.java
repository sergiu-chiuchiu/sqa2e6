package com.sqa.onlinepizzastore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AppUserPaswordChangeDto extends AppUserPassResetDto {
	@NotBlank
	protected String oldPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
}
