package com.sqa.onlinepizzastore.entitites;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cart_detail")
public class AppCartDetail {
	
	private Long id;
	

	private AppCart appCart;
	
	
	private AppProduct appProduct;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_detail_id", unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	private Date cartDate;
	

	private Integer cartQty;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "cart_date", nullable = false)
	public Date getCartDate() {
		return cartDate;
	}
	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}
	

	@Column(name = "cart_qty", nullable = false)
	public Integer getCartQty() {
		return cartQty;
	}
	public void setCartQty(Integer cartQty) {
		this.cartQty = cartQty;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_no")  
	public AppCart getAppCart() {
		return appCart;
	}
	public void setAppCart(AppCart appCart) {
		this.appCart = appCart;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")  
	public AppProduct getAppProduct() {
		return appProduct;
	}
	public void setAppProduct(AppProduct appProduct) {
		this.appProduct = appProduct;
	}
	
	
	
}
