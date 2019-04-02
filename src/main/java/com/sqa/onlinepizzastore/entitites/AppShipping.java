package com.sqa.onlinepizzastore.entitites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_shipping", uniqueConstraints = {
		@UniqueConstraint(columnNames = "shipping_id")
})
public class AppShipping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shipping_id", unique = true)
	private Long shippingId;
	
	@NotBlank
	@Column(name = "ship_method", nullable = false)
	private String shipMethod;
	
	@NotBlank
	@Column(name = "ship_charge", nullable = false)
	private Double shipCharge;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "ship_date", nullable = false)
	private Date shipDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receipt_id")
	private AppReceipt appReceipt;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id") 
	private AppAddress appAddress;
	
	public AppAddress getAppAddress() {
		return appAddress;
	}

	public void setAppAddress(AppAddress appAddress) {
		this.appAddress = appAddress;
	}

	@OneToOne(mappedBy = "appShipping", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private AppOrder appOrder;
	
	public AppOrder getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(AppOrder appOrder) {
		this.appOrder = appOrder;
	}

	public AppReceipt getAppReceipt() {
		return appReceipt;
	}

	public void setAppReceipt(AppReceipt appReceipt) {
		this.appReceipt = appReceipt;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}

	public Double getShipCharge() {
		return shipCharge;
	}

	public void setShipCharge(Double shipCharge) {
		this.shipCharge = shipCharge;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	
	
	
}
