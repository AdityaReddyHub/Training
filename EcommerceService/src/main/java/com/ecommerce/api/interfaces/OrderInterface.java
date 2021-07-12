package com.ecommerce.api.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.api.entities.UserOrder;

@FeignClient(url="http://localhost:8083/userorders",value = "order-service")
public interface OrderInterface {
	
	@PostMapping("/")
	public ResponseEntity<UserOrder> createOrder(@Valid @RequestBody UserOrder userOrder);
	
	@GetMapping("/{ordername}")
	public ResponseEntity<UserOrder> findUserOrder(@PathVariable String ordername);
	
	@GetMapping("/")
	public ResponseEntity<List<UserOrder>> findAllUserOrders();

}
