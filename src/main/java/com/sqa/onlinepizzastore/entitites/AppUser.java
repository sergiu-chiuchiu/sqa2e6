package com.sqa.onlinepizzastore.entitites;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_user")
public class AppUser {
	
	@Id
	@Email
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	@Column(name = "user_name")
	private String userName;
	@Min(value = 0)
	@Max(value = 2000)
	private Integer points = 0;
	
	@ManyToMany(cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST
	})
	@JoinTable(name = "user_role"
	, joinColumns = {@JoinColumn(referencedColumnName = "email")}
	, inverseJoinColumns = {@JoinColumn(referencedColumnName="role_id")})
    @JsonManagedReference
	private Set<AppRole> appRoles;
	
	
	
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
	
	public AppUser() {
		super();
	}

}
