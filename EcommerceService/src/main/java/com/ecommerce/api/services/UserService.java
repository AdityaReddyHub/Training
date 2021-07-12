package com.ecommerce.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.entities.Product;
import com.ecommerce.api.entities.User;
import com.ecommerce.api.entities.UserCart;
import com.ecommerce.api.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository uRepos;
	
	public User addUser(User user) {
		return uRepos.save(user);
	}
	
	public UserCart saveToCart(Product product) {  
		return uRepos.save(product);
	}
}
