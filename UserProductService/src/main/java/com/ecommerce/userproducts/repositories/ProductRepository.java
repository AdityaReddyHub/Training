package com.ecommerce.userproducts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userproducts.entities.Product;
import com.ecommerce.userproducts.entities.UserCart;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	UserCart save(UserCart cart);

	
}
