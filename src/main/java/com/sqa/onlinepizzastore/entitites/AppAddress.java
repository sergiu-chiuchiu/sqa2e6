package com.sqa.onlinepizzastore.entitites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_address", uniqueConstraints = {
		@UniqueConstraint(columnNames = "address_id")
})
public class AppAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id",unique = true, nullable = false)
	private Long addressId;
	
	@NotBlank
	@Column(name = "name",unique = true, nullable = false)
	private String name;
	
	@NotBlank
	@Column(name = "city", nullable = false)
	private String city;
	
	@NotBlank
	@Column(name = "state", nullable = false)
	private String state;
	
	@NotBlank
	@Column(name = "zip_code", nullable = false)
	private Integer zipCode;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email") 
	private AppUser appUser;
	
	@OneToMany(mappedBy="appAddress")
	private Set<AppReceipt> receipts  = new HashSet<AppReceipt>();
	
	@OneToMany(mappedBy="appAddress")
	private Set<AppShipping> shippings  = new HashSet<AppShipping>();
	
	public AppUser getAppUser() {
		return appUser;
	}

	public Set<AppShipping> getShippings() {
		return shippings;
	}

	public void setShippings(Set<AppShipping> shippings) {
		this.shippings = shippings;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Set<AppReceipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(Set<AppReceipt> receipts) {
		this.receipts = receipts;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	
	
}
