package com.bank.reigister.customer.controllers;

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

import com.bank.reigister.customer.dtos.BeneficiaryBean;
import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.services.CustomerService;

@RestController
@RequestMapping("/addBenificiaryAcc")
public class AddBeneficiaryAccountsController {
	
	private static final Logger log = LoggerFactory.getLogger(AddBeneficiaryAccountsController.class);
	
	//private static final ModelMapper mapper = new ModelMapper(); 
	
	//BeneficiaryBean beneficiary = mapper.map(BeneficiaryAccounts, destinationType)
	
	
	@Autowired
	CustomerService service;

	@PostMapping("/")
/*	public ResponseEntity<?> addAccount(@Valid @RequestBody BeneficiaryBean beneficiary,
			@RequestParam(value="username") String username,@RequestParam(value="passwrod")
						String password) { */
	
	public ResponseEntity<?> addAccount(@Valid @RequestBody BeneficiaryBean beneficiary) {
		
		AccountDetails accountDetails =service.addBeneficrAccounts(beneficiary,beneficiary.getUsername(),beneficiary.getPassword());
		
		if(accountDetails!=null) { 
			log.info("Beneficiary Account has been added successfully..");
			return new ResponseEntity<>(accountDetails,HttpStatus.OK); 
		}else {
			log.info("Beneficiary Account failed to Add");
			return new ResponseEntity<>(accountDetails,HttpStatus.BAD_REQUEST);
		}
	}
	
}
