package com.ecommerce.userproducts.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String productname;
	private String username;
	private double price;
	
	
	@OneToOne(cascade = CascadeType.ALL) 
	private UserOrder order;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
	private List<Product> product;
	 
	
	
	

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	  public UserOrder getOrder() { return order; }
	  
	  public void setOrder(UserOrder order) { this.order = order; }
	 
	
}
