package com.ecommerce.api.interfaces;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.api.entities.CustomerDetails;

@FeignClient(url="http://localhost:8084/customerregister", value="banking-service1")
public interface CustomerRegisterInterface {
	
	@PostMapping("/addUser")
	public ResponseEntity<Map<String,String>> createUser(@RequestBody CustomerDetails custDetails);
}
