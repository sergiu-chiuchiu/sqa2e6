package com.sqa.onlinepizzastore.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AppUserPersonalDataDto extends AppUserEmailDto {
	@NotBlank
	@Size(min = 3, max = 30)
	protected String userName;
	
	@NotNull
	@PositiveOrZero
	protected Integer points = 0;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	protected Date birthDate;
	
	protected String gender;

	
	public  String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
