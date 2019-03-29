package com.sqa.onlinepizzastore.entitites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_user")
public class AppUser {
	
	@Id
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	@Column(name = "user_name")
	private String userName;
	@Min(value = 0)
	@Max(value = 2000)
	private Integer points = 0;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	private String gender;
	
	@ManyToMany(cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST
	})
	@JoinTable(name = "user_role"
	, joinColumns = {@JoinColumn(referencedColumnName = "email")}
	, inverseJoinColumns = {@JoinColumn(referencedColumnName="role_id")})
    @JsonManagedReference
	private Set<AppRole> appRoles = new HashSet<AppRole>();
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<AppRole> getAppRoles() {
		return appRoles;
	}
	public void setAppRoles(Set<AppRole> appRoles) {
		this.appRoles = appRoles;
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
	
	public void addAppRole(AppRole appRole) {
		this.appRoles.add(appRole);
	}
	
	public AppUser() {
		super();
	}

}
