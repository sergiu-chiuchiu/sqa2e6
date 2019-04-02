package com.sqa.onlinepizzastore.entitites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "app_payment", uniqueConstraints = {
		@UniqueConstraint(columnNames = "payment_id")
})
public class AppPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id",unique = true, nullable = false)
	private Long paymentId;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	@Column(name = "pay_date", nullable = false)
	private Date payDate;
	
	@NotBlank
	@Column(name = "pay_type", nullable = false)
	private String payType;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email") 
	private AppUser appUser;
	
	public Long getPaymentId() {
		return paymentId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@NotBlank
	@Column(name = "pay_amount", nullable = false)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCumultatePoints() {
		return cumultatePoints;
	}

	public void setCumultatePoints(Integer cumultatePoints) {
		this.cumultatePoints = cumultatePoints;
	}

	private Double amount;
	
	private Integer cumultatePoints;
	
}
