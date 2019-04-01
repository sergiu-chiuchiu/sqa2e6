package com.sqa.onlinepizzastore.entitites;



import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ingredient", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ing_id")
})
public class Ingredient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ing_id;
	
	@Column(unique = true, nullable = false)
	private String ing_name;
	
	@NotBlank(message = "The type of ingredient is mandatory!")
	private String ing_type;
	
	@NotNull(message = "The ingredient is allergen?")
	private Boolean	ing_allergen;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00") 
	private Double ing_weight;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double ing_price;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double ing_quantity;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double energy;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double fat;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double carbohydrate;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double fibres;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double proteine;
	
	@DecimalMin("0.00") 
	@DecimalMax("99999.00")
	private Double salt;
	
	@ManyToMany(mappedBy="ingredients")
    @JsonBackReference
	private Set<Customized> customs;

	public Integer getIng_id() {
		return ing_id;
	}
	
	public void setIng_id(Integer ing_id) {
		this.ing_id = ing_id;
	}

	public String Ing_type() {
		return ing_name;
	}

	
	public String getIng_name() {
		return ing_name;
	}

	public void setIng_name(String ing_name) {
		this.ing_name = ing_name;
	}

	public String getIng_type() {
		return ing_type;
	}

	public void setIng_type(String ing_type) {
		this.ing_type = ing_type;
	}

	public Boolean getIng_allergen() {
		return ing_allergen;
	}

	public void setIng_allergen(Boolean ing_allergen) {
		this.ing_allergen = ing_allergen;
	}

	public Double getIng_weight() {
		return ing_weight;
	}

	public void setIng_weight(Double ing_weight) {
		this.ing_weight = ing_weight;
	}

	public Double getIng_price() {
		return ing_price;
	}

	public void setIng_price(Double ing_price) {
		this.ing_price = ing_price;
	}

	public Double getIng_quantity() {
		return ing_quantity;
	}

	public void setIng_quantity(Double ing_quantity) {
		this.ing_quantity = ing_quantity;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Double getFibres() {
		return fibres;
	}

	public void setFibres(Double fibres) {
		this.fibres = fibres;
	}

	public Double getProteine() {
		return proteine;
	}

	public void setProteine(Double proteine) {
		this.proteine = proteine;
	}

	public Double getSalt() {
		return salt;
	}

	public void setSalt(Double salt) {
		this.salt = salt;
	}

	public Set<Customized> getCustoms() {
		return customs;
	}

	public void setCustoms(Set<Customized> customs) {
		this.customs = customs;
	}
	public void addCustomized(Customized custom) {
		this.customs.add(custom);
	}

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
}
