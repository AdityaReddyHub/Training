package com.bank.reigister.customer.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.entities.CustomerDetails;
import com.bank.reigister.customer.services.CustomerService;
import com.google.gson.*;

@RestController
@RequestMapping("/customerregister")
public class CustomerRegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerRegisterController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/addUser")
	public ResponseEntity<Map<String,String>> createUser(@RequestBody CustomerDetails custDetails){
		
		AccountDetails acDtls = customerService.saveCustomerDetails(custDetails);
		
		Map<String,String> userDetls= new HashMap<String, String>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		
		userDetls.put("Customer AC Number", acDtls.getAccountnumber());
		userDetls.put("Bank Name", acDtls.getBankname());
		userDetls.put("Branch Name", acDtls.getBranchname());
		userDetls.put("IFSC Code", acDtls.getIfsccode());
		userDetls.put("Pan Number", String.valueOf(acDtls.getPannumber()));
		userDetls.put("Amount", String.valueOf(acDtls.getAmount()));
		userDetls.put("Beneficiaries", gson.toJson(acDtls.getBeneficiaryAccs()));
		userDetls.put("Credentials", gson.toJson(acDtls.getCustCreds()));
		
		return new ResponseEntity<Map<String,String>>(userDetls,HttpStatus.OK);
		
		
	}
	
	
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
