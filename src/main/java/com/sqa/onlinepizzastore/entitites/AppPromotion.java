package com.sqa.onlinepizzastore.entitites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_promotion", uniqueConstraints = {
		@UniqueConstraint(columnNames = "prom_id")
})
public class AppPromotion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prom_id", unique = true)
	private Long promId;
	
	@NotBlank
	@Column(name = "prom_name", unique = true, nullable = false)
	private String promName;
	
	@NotBlank
	@Column(name = "value", nullable = false)
	private Double value;
	
	@NotBlank
	@Column(name = "percentage", nullable = false)
	private Double percentage;
	
	@OneToMany(mappedBy="appPromotion")
	private Set<AppOrder> orders= new HashSet<AppOrder>();
	
	public Set<AppOrder> getOrders() {
		return orders;
	}
	public void setOrders(Set<AppOrder> orders) {
		this.orders = orders;
	}
	public Long getPromId() {
		return promId;
	}
	public void setPromId(Long promId) {
		this.promId = promId;
	}
	public String getPromName() {
		return promName;
	}
	public void setPromName(String promName) {
		this.promName = promName;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(AppOrder appOrder) {
		this.value = appOrder.getTotalAmt() * getPercentage();
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
	
}
