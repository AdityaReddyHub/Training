package com.bank.reigister.customer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.services.CustomerService;

@RestController
@RequestMapping("/customerlogin")
public class CustomerLoginController {

	private static final Logger log = LoggerFactory.getLogger(CustomerLoginController.class);
	
	
	@Autowired
	CustomerService loginService;
	
	@PostMapping("/")
	public ResponseEntity<?> customerLogin(@RequestParam(value = "username") String username, @RequestParam(value="password") String password) {
		
		log.info("Customer logging...");
		
		AccountDetails accDetails = loginService.getLogin(username, password);
		if(accDetails!=null) {
			log.info("Successfully logged in");
			return new ResponseEntity<>(accDetails,HttpStatus.OK);
		}else {
			log.info("Unable to fetch the login details..");
			return new ResponseEntity<>("No Datat Found",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
/*	@Autowired
	CustomerService loginService;
	
	@PostMapping("/")
	public ResponseEntity<?> getLoginAndAccountNumber(@RequestParam(value="username") String username,@RequestParam(value="password") String password){
		
		log.debug("Customer logging...."+username+" and "+password);
		
		String accountNumber=null;
		
		accountNumber =  loginService.getAccountNumber(username,password);
		
		if(accountNumber!=null&&!accountNumber.isEmpty()) {
			log.info("Account Number Recieved Successfully");
			return new ResponseEntity<> (accountNumber,HttpStatus.OK);
		}else
			log.info("Invalid User Credentials");
			return new ResponseEntity<> ("No Account Number Found",HttpStatus.BAD_REQUEST);
		
	} */
	
	
	
	
}
