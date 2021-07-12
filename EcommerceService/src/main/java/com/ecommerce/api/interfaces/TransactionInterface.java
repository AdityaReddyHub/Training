package com.ecommerce.api.interfaces;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="http://localhost:8084/transaction", value="banking-service")
public interface TransactionInterface {
	
	@PostMapping
	public ResponseEntity<Map<String,String>> buyProducts(@RequestParam(value = "productname") String productname,@RequestParam(value = "username") String username,
			@RequestParam(value="password") String password,@RequestParam(value = "beneficiaryAccNumber") String beneficiaryAccNumber,
			@RequestParam(value="transactAmount") int transactAmount);
	
	
	

}
