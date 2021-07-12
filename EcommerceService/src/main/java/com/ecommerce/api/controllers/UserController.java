package com.ecommerce.api.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.entities.CustomerDetails;
import com.ecommerce.api.entities.Product;
import com.ecommerce.api.entities.UserOrder;
import com.ecommerce.api.interfaces.CustomerRegisterInterface;
import com.ecommerce.api.interfaces.OrderInterface;
import com.ecommerce.api.interfaces.ProductInterface;
import com.ecommerce.api.interfaces.TransactionInterface;
import com.ecommerce.api.services.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	ProductInterface productsInterface;
	
	@Autowired
	OrderInterface ordersInterface;
	
	@Autowired
	TransactionInterface transInterface;
	
	@Autowired
	CustomerRegisterInterface custInterface;
	
	/*
	 * @Autowired CircuitBreakerFactory cicuiteBreakerFactory;
	 */
	
/*	@Autowired
	UserOrder uorder;
*/	
	@Autowired
	UserService service;
	
/*	@PostMapping("/adduser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User user1 = service.addUser(user);
		
		if(user1!=null) {
			log.info("User Creation Completed Successfully!");
			return new ResponseEntity<User>(user1,HttpStatus.OK);
		}else {
			log.info("User Creation Failed");
			return new ResponseEntity<User>(user1,HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	@PostMapping("/addcustomer")
	public ResponseEntity<Map<String,String>> addCustomer(@RequestBody CustomerDetails custDtls){
		
		
		return custInterface.createUser(custDtls);
	}
	
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping("/getproducts")
	public ResponseEntity<?> searchProductsToBuy() {
		
		//CircuitBreaker circuiteBreaker = cicuiteBreakerFactory.create("product-service");
		
		log.info("Calling Products for search product");
		
		//return circuiteBreaker.run(()->productsInterface.allAvailableProducts(),throwble -> getDefaultInfo());
		
		
		 return productsInterface.allAvailableProducts();
		
	}
	
	/*
	 * private ResponseEntity<?> getDefaultInfo() { // TODO Auto-generated method
	 * stub
	 * 
	 * String msg = "Product Service is down, Please try after some time"; return
	 * new ResponseEntity<>(msg,HttpStatus.OK); }
	 */

	@GetMapping("/{name}")
	public ResponseEntity<Product> searchProduct(@PathVariable String name) {
		log.info("Calling Products for Fetching products");
		return productsInterface.findProduct(name);
	}
	
	@PostMapping("/")
	public ResponseEntity<Map<String,String>> purchaseProduct(@RequestParam(value = "productname") String productname,@RequestParam(value = "username") String username,
			@RequestParam(value="password") String password,@RequestParam(value = "beneficiaryAccNumber") String beneficiaryAccNumber,
			@RequestParam(value="transactAmount") int transactAmount){
		
		log.info("Calling Transaction Process for buy product");
		
	/*	uorder.setId(0);
		uorder.setName(productname);
		uorder.setOrderprice(transactAmount);
		
		ordersInterface.createOrder(uorder); 
	*/	
		//ordersInterface.createOrder(order);
		return transInterface.buyProducts(productname,username, password, beneficiaryAccNumber,transactAmount);
	}
	
	@GetMapping("/Orders/search")
	public ResponseEntity<UserOrder> searchUserOrder(@RequestParam(value="ordername") String ordername){
		return ordersInterface.findUserOrder(ordername);
	}
	
	@GetMapping("/Orders/getallorders")
	public ResponseEntity<List<UserOrder>> allUserOrders(){
		return ordersInterface.findAllUserOrders();
	}
	
	
	
}
