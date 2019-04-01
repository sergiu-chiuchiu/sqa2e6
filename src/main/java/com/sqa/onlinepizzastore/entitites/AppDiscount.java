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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_discount", uniqueConstraints = {
		@UniqueConstraint(columnNames = "discount_id")
})
public class AppDiscount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "discount_id", unique = true)
	private Long discountId;
	
	@NotBlank
	@Column(name = "discount_name", unique = true, nullable = false)
	private String discountName;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "discount_date", nullable = false)
	private Date discountDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private AppOrder appOrder;

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Date getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Date discountDate) {
		this.discountDate = discountDate;
	}

	public AppOrder getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(AppOrder appOrder) {
		this.appOrder = appOrder;
	}
	
	
	
}
