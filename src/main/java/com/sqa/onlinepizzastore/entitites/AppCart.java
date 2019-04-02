package com.sqa.onlinepizzastore.entitites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "app_cart", uniqueConstraints = {
		@UniqueConstraint(columnNames = "cart_no")
})

public class AppCart {

	private Long cartNo;
	
	private char active;
	
	private String customerEmail;

	private AppOrder appOrder;
	
	private AppUser appUser;
	
	@OneToOne(mappedBy = "appCart", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	private Set<AppCartDetail> cartDetails= new HashSet<AppCartDetail>();
	
	@OneToMany(mappedBy="appCart")
	public Set<AppCartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<AppCartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public void addCartDetail(AppCartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_no", unique = true)
	public Long getCartNo() {
		return cartNo;
	}

	public void setCartNo(Long cartNo) {
		this.cartNo = cartNo;
	}

	@Column(name = "active")
	public char getActive() {
		return active;
	}
	
	public void setActive(char active) {
		this.active = active;
	}

	@Column(name = "customer_email", unique = true, nullable = false)
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	
	@OneToOne(mappedBy = "appCart", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	public AppOrder getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(AppOrder appOrder) {
		this.appOrder = appOrder;
	}

	public AppCart() {
		super();
		// TODO Auto-generated constructor stub
	}
}
