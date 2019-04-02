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
@Table(name = "app_receipt", uniqueConstraints = {
		@UniqueConstraint(columnNames = "receipt_id")
})

public class AppReceipt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_id", unique = true)
	private Long receiptId;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "receipt_date", nullable = false)
	private Date receiptDate;
	
	@NotBlank
	@Column(name = "receipt_amount", nullable = false)
	private Double receiptAmount;

	@OneToOne(mappedBy = "appReceipt", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private AppOrder appOrder;
	
	@OneToOne(mappedBy = "appReceipt", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private AppShipping appShipping;
	
	public AppOrder getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(AppOrder appOrder) {
		this.appOrder = appOrder;
	}

	public AppShipping getAppShipping() {
		return appShipping;
	}

	public void setAppShipping(AppShipping appShipping) {
		this.appShipping = appShipping;
	}

	public AppAddress getAppAddress() {
		return appAddress;
	}

	public void setAppAddress(AppAddress appAddress) {
		this.appAddress = appAddress;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id") 
	private AppAddress appAddress;
	
	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(Double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	
	
	
}
