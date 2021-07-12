package com.ecommerce.userproducts.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userproducts.dtos.OrderLineItems;
import com.ecommerce.userproducts.entities.Product;
import com.ecommerce.userproducts.entities.UserCart;
import com.ecommerce.userproducts.entities.UserOrder;
import com.ecommerce.userproducts.interfaces.TransactionInterface;
import com.ecommerce.userproducts.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
		private static final Logger log = LoggerFactory.getLogger(ProductController.class);
		
		
		@Autowired
		ProductService service;
		
		@Autowired
		TransactionInterface transInterface;
		
		
		@PostMapping("/buyPorduct")
		public ResponseEntity<Map<String,String>> buyProducts(@RequestParam(value = "productname") String productname,@RequestParam(value = "username") String username,
				@RequestParam(value="password") String password,@RequestParam(value = "beneficiaryAccNumber") String beneficiaryAccNumber,
				@RequestParam(value="transactAmount") int transactAmount){
			
			
			UserOrder order = new UserOrder();
			order.setId(0);
			order.setAmount(transactAmount);
			order.setProductname(productname);
			UserCart cart=new UserCart();
			cart.setId(0);
			cart.setOrder(order);
			cart.setPrice(transactAmount);
			cart.setProductname(productname);
			cart.setUsername(username);
			order.setCart(cart);
			createOrder(order);
			
			return transInterface.buyProducts(productname, username, password, beneficiaryAccNumber, transactAmount);
			
		}
		
		@SuppressWarnings("unused")
		@PostMapping("/add/AddProductToCart")
		public ResponseEntity<UserCart> addProductToCart(@RequestBody OrderLineItems items) {
			
			
			//List<Product> products = items.getProducts();
			
		/*
		 * for(int i=0;i<=products.size();i++) {
		 * 
		 * UserCart cart = new UserCart(); //cart.setOrder(order); cart.setId(0);
		 * cart.setPrice(products.get(i).getPrice());
		 * cart.setProductname(products.get(i).getName());
		 * cart.setProduct(products.get(0));
		 * 
		 * UserCart cart1= service.saveUserCart(cart);
		 * 
		 * if(cart1!=null) { return new ResponseEntity<UserCart>(cart1,HttpStatus.OK);
		 * }else { return new ResponseEntity<UserCart>(cart1,HttpStatus.BAD_REQUEST); }
		 * 
		 * }
		 */
			
			 UserCart cart1= service.saveUserCart(items);
			 
			 if(cart1!=null){
				 return new ResponseEntity<UserCart>(cart1,HttpStatus.OK);
			}else{
				return new ResponseEntity<UserCart>(cart1,HttpStatus.BAD_REQUEST); }
			
			
			
		}
		
		@PostMapping("/add/product/addproduct")
		public ResponseEntity<Product> addProduct(@RequestBody Product product) {
			
			
			
			Product product1 = service.saveProduct(product);
			
			//UserCart cart= service.saveUserCart(cart);
			
			if(product1!=null) {
				return new ResponseEntity<Product>(product1,HttpStatus.OK);
			}else {
				return new ResponseEntity<Product>(product1,HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@GetMapping("/findProduct/{name}")
		public ResponseEntity<Product> findProduct(@PathVariable String name) {
			Product product1 = service.searchProduct(name);
			
			if(product1!=null) {
				return new ResponseEntity<Product>(product1,HttpStatus.OK);
			}else {
				return new ResponseEntity<Product>(product1,HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@GetMapping("/products/AllProducts")
		public ResponseEntity<List<Product>> allAvailableProducts() {
			List<Product> products = service.searchProducts();
			
			if(products!=null && !products.isEmpty()) {
				return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Product>>(products,HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@PostMapping("/create/order/PlaceOrder")
		public ResponseEntity<UserOrder> createOrder(@Valid @RequestBody UserOrder userOrder) {
			
			
			
			UserOrder uOrder = service.saveOrder(userOrder);
			
			if(uOrder!=null) {
				log.info("User Order Created");
				return new ResponseEntity<UserOrder>(uOrder,HttpStatus.OK);
			}else {
				log.info("User Order Not Created");
				return new ResponseEntity<UserOrder>(uOrder,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/{ordername}")
		public ResponseEntity<UserOrder> findUserOrder(@PathVariable String ordername) {
			
			UserOrder uOrder = service.getUserOrder(ordername);
			
			if(uOrder!=null) {
				log.info("Fetched User Order Successfully");
				return new ResponseEntity<UserOrder>(uOrder,HttpStatus.OK);
			}else {
				log.info("Failed to Fectch User Order ");
				return new ResponseEntity<UserOrder>(uOrder,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/AllOrders")
		public ResponseEntity<List<UserOrder>> findAllUserOrders() {
			List<UserOrder> uOrders = service.getUserOrders();
			
			if(uOrders!=null && !uOrders.isEmpty()) {
				log.info("Fetched User Order Successfully");
				return new ResponseEntity<List<UserOrder>>(uOrders,HttpStatus.OK);
			}else {
				log.info("Failed to Fectch User Order ");
				return new ResponseEntity<List<UserOrder>>(uOrders,HttpStatus.BAD_REQUEST);
			}
		}
}
