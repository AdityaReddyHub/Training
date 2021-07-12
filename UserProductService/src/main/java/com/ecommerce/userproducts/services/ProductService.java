package com.ecommerce.userproducts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.userproducts.dtos.OrderLineItems;
import com.ecommerce.userproducts.entities.Product;
import com.ecommerce.userproducts.entities.UserCart;
import com.ecommerce.userproducts.entities.UserOrder;
import com.ecommerce.userproducts.repositories.ProductRepository;
import com.ecommerce.userproducts.repositories.UserOrderRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository pRepos;
	
	@Autowired
	UserOrderRepository oRepos;
	
	public Product saveProduct(Product product) {
		return pRepos.save(product);
	}
	
	public UserCart saveUserCart(OrderLineItems items) {
		 
		List<Product> products = items.getProducts();
		
		double orderAmt = 0;
			for(int i=0; i<products.size();i++) {
				orderAmt=products.get(i).getPrice();
			}
		
			UserOrder order = new UserOrder();
			order.setId(0);
			order.setAmount(orderAmt);
			order.setProductname("Usertrans");
			
			UserCart cart = new UserCart();
			cart.setId(0);
			cart.setProduct(items.getProducts());
			
			return pRepos.save(cart);
		
	}
	
	public Product searchProduct(String name) {
		return pRepos.findByName(name);
	}
	
	public List<Product> searchProducts() {
		return pRepos.findAll();
	}
	
	public UserOrder saveOrder(UserOrder order) {
		return oRepos.save(order);
	}
	
	public UserOrder getUserOrder(String ordername) {
		return oRepos.findByproductname(ordername);
	}
	
	public List<UserOrder> getUserOrders(){
		return oRepos.findAll();
	}

}
