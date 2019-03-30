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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "app_role", uniqueConstraints = {
		@UniqueConstraint(columnNames = "role_id")
})

public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", unique = true)
	private Long roleId;
	
	@NotBlank
	@Column(name = "role_name", unique = true, nullable = false, length = 100)
	private String roleName;

	@ManyToMany(mappedBy = "appRoles")
    @JsonBackReference
	private Set<AppUser> users;
	
	


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Set<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AppUser> users) {
		this.users = users;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void addUser(AppUser appUser) {
		this.users.add(appUser);
	}
	
	
	public AppRole() {
		super();
	}
	
	
	
}
