package com.sqa.onlinepizzastore.entitites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_order", uniqueConstraints = {
		@UniqueConstraint(columnNames = "order_id")
})
public class AppOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", unique = true)
	private Long orderId;
	
	@NotBlank
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	
	@NotBlank
	@Column(name = "total_amt", nullable = false)
	private Double totalAmt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_no")
	private AppCart appCart;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public AppCart getAppCart() {
		return appCart;
	}

	public void setAppCart(AppCart appCart) {
		this.appCart = appCart;
	}

	
	
}
