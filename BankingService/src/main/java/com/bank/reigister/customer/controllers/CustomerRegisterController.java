package com.bank.reigister.customer.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.entities.CustomerDetails;
import com.bank.reigister.customer.services.CustomerService;

@RestController
@RequestMapping("/customerregister")
public class CustomerRegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerRegisterController.class);
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("/")
	public AccountDetails customerRegistration(@Valid @RequestBody CustomerDetails custDetails) {
		
		return customerService.saveCustomerDetails(custDetails);
		
	}
	
	
/*	@PostMapping("/")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRegistrationInfo customerRegistration){
		
		log.debug("Customer Registration process started..."+customerRegistration.getAddress());
		
		CustomerRegistrationInfo customerResult =customerService.customerReg(customerRegistration);;
		return new ResponseEntity<>(customerResult,HttpStatus.OK);
	}
	
	@PostMapping("/{firstname}")
	public List<String> getCredentialsForCustomer(@PathVariable String firstname) {
		log.debug("getting credentials for customer  "+firstname);
		return customerService.getCredentialsForCustomer(firstname);
	}*/
}
