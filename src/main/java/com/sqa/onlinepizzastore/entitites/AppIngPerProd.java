package com.sqa.onlinepizzastore.entitites;

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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ing_per_prod")
public class AppIngPerProd {

	private Long id;
	
	private Ingredient ingredient;
	
	private AppProduct product;
	
	private Integer ingQuantity;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ing_per_prod_id", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ing_id")  
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")  
	public AppProduct getProduct() {
		return product;
	}

	public void setProduct(AppProduct product) {
		this.product = product;
	}
	
	@NotBlank
	@Column(name = "ing_quantity", nullable = false)
	public Integer getIngQuantity() {
		return ingQuantity;
	}

	public void setIngQuantity(Integer ingQuantity) {
		this.ingQuantity = ingQuantity;
	}
	
}
