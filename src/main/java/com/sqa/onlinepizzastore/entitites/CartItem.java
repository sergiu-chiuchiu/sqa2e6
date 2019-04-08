package com.sqa.onlinepizzastore.entitites;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")  
	private AppProduct product;
	
	@Column(name = "qty", nullable = false)
	private int qty;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_no")  
	private AppCart appCart;
	
	
	public AppCart getAppCart() {
		return appCart;
	}

	public void setAppCart(AppCart appCart) {
		this.appCart = appCart;
	}



	public AppProduct getProduct() {
		return product;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Long id, AppProduct product, int qty) {
		super();
		this.id = id;
		this.product = product;
		this.qty = qty;
	}

	public void setProduct(AppProduct product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
