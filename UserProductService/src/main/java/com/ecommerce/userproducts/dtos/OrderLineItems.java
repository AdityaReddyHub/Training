package com.ecommerce.userproducts.dtos;

import java.util.List;

import com.ecommerce.userproducts.entities.Product;

public class OrderLineItems {

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
