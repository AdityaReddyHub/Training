package com.ecommerce.userproducts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message="please slect product")
	private String name;
	private double price;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserCart cart;
	
	
	
	public UserCart getCart() {
		return cart;
	}
	public void setCart(UserCart cart) {
		this.cart = cart;
	}
	/*
	 * @OneToOne(cascade = CascadeType.ALL) private Order order;
	 * 
	 * public Order getOrder() { return order; } public void setOrder(Order order) {
	 * this.order = order; }
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
