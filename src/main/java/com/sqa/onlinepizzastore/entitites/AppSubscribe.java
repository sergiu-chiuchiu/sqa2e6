package com.sqa.onlinepizzastore.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "app_subscribe")
public class AppSubscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sub_id", unique = true, nullable = false)
	private Long subscribeId;
	
	@Email
	@Column(name = "email", unique = true)
	private String email;
	
	public Long getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(Long subscribeId) {
		this.subscribeId = subscribeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
