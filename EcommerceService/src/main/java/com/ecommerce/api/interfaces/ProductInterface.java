package com.ecommerce.api.interfaces;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.api.entities.Product;
import com.ecommerce.api.entities.UserOrder;

@FeignClient(url="http://localhost:8083/products", value="product-service")
public interface ProductInterface {
	
	
	@PostMapping("/buyPorduct")
	public ResponseEntity<Map<String,String>> buyProducts(@RequestParam(value = "productname") String productname,@RequestParam(value = "username") String username,
			@RequestParam(value="password") String password,@RequestParam(value = "beneficiaryAccNumber") String beneficiaryAccNumber,
			@RequestParam(value="transactAmount") int transactAmount);
	
	@PostMapping("/add/AddProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product);
	
	@GetMapping("/findProduct/{name}")
	public ResponseEntity<Product> findProduct(@PathVariable String name) ;
	
	@GetMapping("/products/AllProducts")
	public ResponseEntity<List<Product>> allAvailableProducts();
	
	@PostMapping("/create/order/PlaceOrder")
	public ResponseEntity<UserOrder> createOrder(@Valid @RequestBody UserOrder userOrder);
	
	
	@GetMapping("/{ordername}")
	public ResponseEntity<UserOrder> findUserOrder(@PathVariable String ordername);
	
	@GetMapping("/AllOrders")
	public ResponseEntity<List<UserOrder>> findAllUserOrders();
	
	
	
	

}
