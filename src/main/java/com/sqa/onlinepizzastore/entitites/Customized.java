package com.sqa.onlinepizzastore.entitites;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "customized")
public class Customized {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customized_id;
	
	@Column(unique=true)
	@Size(min=3, max=50)
	private String customized_name;
	

	@NotNull(message="The energy is mandatory!")
	private Integer  customized_energy; 

	@NotBlank(message="Select one countertop!")
	private String countertops;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "cust_ing"
	, joinColumns = {@JoinColumn(referencedColumnName = "customized_id")}
	, inverseJoinColumns = {@JoinColumn(referencedColumnName="ing_id")})
    @JsonManagedReference
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	
	
	public Integer getCustomized_id() {
		return customized_id;
	}



	public void setCustomized_id(Integer customized_id) {
		this.customized_id = customized_id;
	}



	public String getCustomized_name() {
		return customized_name;
	}



	public void setCustomized_name(String customized_name) {
		this.customized_name = customized_name;
	}


	public Integer getCustomized_energy() {
		return customized_energy;
	}

	public void setCustomized_energy(Integer customized_energy) {
		this.customized_energy = customized_energy;
	}



	public String getCountertops() {
		return countertops;
	}



	public void setCountertops(String countertops) {
		this.countertops = countertops;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	public Customized() {
		super();
		// TODO Auto-generated constructor stub
	}

}