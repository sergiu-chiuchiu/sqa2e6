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
	@Column(name = "role_id", unique = true, nullable = false)
	private Integer roleId;
	
	@NotBlank
	@Column(name = "role_name", unique = true, nullable = false, length = 100)
	private String roleName;

	@ManyToMany(mappedBy = "appRoles")
    @JsonBackReference
	private Set<AppUser> users;
	
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public AppRole() {
		super();
	}
	
	
	
}
