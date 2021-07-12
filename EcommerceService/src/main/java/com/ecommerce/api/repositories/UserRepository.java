package com.ecommerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.api.entities.Product;
import com.ecommerce.api.entities.User;
import com.ecommerce.api.entities.UserCart;

public interface UserRepository extends JpaRepository<User, Integer> {

	UserCart save(Product product);

}
