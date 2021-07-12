package com.bank.reigister.customer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.reigister.customer.dtos.TransactionDetails;
import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.entities.BeneficiaryAccounts;
import com.bank.reigister.customer.services.CustomerService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
		
	
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	
	@Autowired
	CustomerService service;
	
/*	@Autowired
	OrderInterface orderInterface;
	
	@Autowired
	UserOrder uOrder;
*/	
	@PostMapping
	public ResponseEntity<Map<String,String>> buyProducts(@RequestParam(value = "productname") String productname,@RequestParam(value = "username") String username,
			@RequestParam(value="password") String password,@RequestParam(value = "beneficiaryAccNumber") String beneficiaryAccNumber,
			@RequestParam(value="transactAmount") int transactAmount){
		
		TransactionDetails tnsDetails = new TransactionDetails();
		tnsDetails.setBeneficiaryAccNumber(beneficiaryAccNumber);
		tnsDetails.setTransactAmount(transactAmount);
		tnsDetails.setProductname(productname);
		
		AccountDetails aDetls = service.customerTransaction(username, password, tnsDetails);
		
		Map<String,String> list = new HashMap<String,String>();
		
		list.put("AccountHoldersACNumber", aDetls.getAccountnumber());
		
		List<BeneficiaryAccounts> bAcs = aDetls.getBeneficiaryAccs();
		String benefcrACNumber=null;
		for(int i=0;i<bAcs.size();i++) {
			benefcrACNumber = bAcs.get(i).getAccountnumber();
		}
		
		list.put("Ecommerce ACNumber", benefcrACNumber);
		list.put("Purchased Amount", String.valueOf(tnsDetails.getTransactAmount()));
		
	/*	uOrder.setId(0);
		uOrder.setName(productname);
		uOrder.setOrderprice(transactAmount);
		
		orderInterface.createOrder(uOrder); */
		
		
		
		return new ResponseEntity<Map<String,String>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<AccountDetails> custTransaction(@RequestParam(value = "username") String username,
			@RequestParam(value="password") String password,@RequestBody TransactionDetails tnsDetails) {
		
		AccountDetails accDetails  = service.customerTransaction(username, password, tnsDetails);
		
		if(accDetails!=null) {
			return new ResponseEntity<AccountDetails>(accDetails,HttpStatus.OK);
		}else {
			return new ResponseEntity<AccountDetails>(accDetails,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
/*	@Autowired
	CustomerService transactionService;
	
	@PostMapping("/")
	public ResponseEntity<?> moneyTransaction(@RequestParam(value="fromAccountNumber") String fromAccountNumber,@RequestParam(value="toAccountNumner") String toAccountNumner,
			@RequestParam(value="amount") String amount){
		
		log.debug("Transaction Initiated for the AccountNumber "+fromAccountNumber+" "+"To Account Number "+toAccountNumner+" with amount "+amount);
		
		String message = transactionService.transactionMoney(fromAccountNumber,toAccountNumner,amount);
		
		if(message.contains("Failed")) {
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		
		
	} */
}
