package com.ecommerce.userproducts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String productname;
	private double amount;
	
	@JsonBackReference	
	  @OneToOne(cascade = CascadeType.ALL ) 
	  private UserCart cart;
	  
	  public UserCart getCart() { return cart; }
	  
	  public void setCart(UserCart cart) { this.cart = cart; }
	 

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double d) {
		this.amount = d;
	}
	
	
}
