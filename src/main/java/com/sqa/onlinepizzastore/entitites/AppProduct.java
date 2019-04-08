package com.sqa.onlinepizzastore.entitites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_product", uniqueConstraints = {
		@UniqueConstraint(columnNames = "product_id")
})
public class AppProduct {
	
	
	private Long productId;
	
	
	private String productName;
	
	
	private String prodQty;
	
	private Character allergens;
	

	private Double energyValue;
	
	
	private Double price;
	

	private Date prodDate;
	

	private Double prodWeight;
	
	private String imageLink;
	
	public Set<AppCartDetail> cartDetails= new HashSet<AppCartDetail>();
	
	public Set<AppIngPerProd> ingPerProd= new HashSet<AppIngPerProd>();

	@OneToMany(mappedBy="appProduct")
	public Set<AppCartDetail> getCartDetails() {
		return cartDetails;
	}

	@OneToMany(mappedBy="product")
	public Set<AppIngPerProd> getIngPerProd() {
		return ingPerProd;
	}

	public void setIngPerProd(Set<AppIngPerProd> ingPerProd) {
		this.ingPerProd = ingPerProd;
	}

	public void setCartDetails(Set<AppCartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public void addCartDetail(AppCartDetail cartDetails) {
        this.cartDetails.add(cartDetails);
    }
	
	public AppProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", unique = true)
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@NotBlank
	@Column(name = "product_name", unique = true, nullable = false)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "prod_qty", nullable = false)
	public String getProdQty() {
		return prodQty;
	}

	public void setProdQty(String prodQty) {
		this.prodQty = prodQty;
	}

	public AppProduct(Long productId, String prodQty) {
		super();
		this.productId = productId;
		this.prodQty = prodQty;
	}

	@Column(name = "allergens", nullable = false)
	public Character getAllergens() {
		return allergens;
	}

	public void setAllergens(Character allergens) {
		this.allergens = allergens;
	}

	@Column(name = "energy_value", nullable = false)
	public Double getEnergyValue() {
		return energyValue;
	}

	public void setEnergyValue(Double energyValue) {
		this.energyValue = energyValue;
	}

	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "prod_date", nullable = false)
	public Date getProdDate() {
		return prodDate;
	}

	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}

	@Column(name = "prod_weight", nullable = false)
	public Double getProdWeight() {
		return prodWeight;
	}

	public void setProdWeight(Double prodWeight) {
		this.prodWeight = prodWeight;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	
	
}
