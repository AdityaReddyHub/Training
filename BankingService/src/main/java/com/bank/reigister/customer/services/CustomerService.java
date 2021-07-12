package com.bank.reigister.customer.services;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.reigister.customer.controllers.CustomerRegisterController;
import com.bank.reigister.customer.dtos.BeneficiaryBean;
import com.bank.reigister.customer.dtos.TransactionDetails;
import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.entities.BeneficiaryAccounts;
import com.bank.reigister.customer.entities.CustomerCredentials;
import com.bank.reigister.customer.entities.CustomerDetails;
import com.bank.reigister.customer.exceptions.CustomerNotFoundException;
import com.bank.reigister.customer.exceptions.DuplicateUserException;
import com.bank.reigister.customer.repositories.AccountDetailsRepository;
import com.bank.reigister.customer.repositories.CustomerRegistrationRepository;
import com.bank.reigister.customer.util.Util;

@Service
public class CustomerService {
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomerRegisterController.class);
	
	@Autowired
	CustomerRegistrationRepository customerRepos;
	@Autowired
	AccountDetailsRepository accountsRepository;
	
	
	@SuppressWarnings("unlikely-arg-type")
	public AccountDetails saveCustomerDetails(CustomerDetails custDetails) {
		
		String panNumber = custDetails.getPannumber();
		
		CustomerDetails custDetails1 = customerRepos.findBypannumber(panNumber);
		
		if(custDetails1!=null) {
			//if (custDetails.getPannumber().equals(custDetails1.getPannumber())) {
				throw new DuplicateUserException("Duplicate Customer..Already Registered");
			//}
		}else {
			String accountNo = Util.generateAccountNumber();
			String ifscCode = "ICIC00089";
			String bankName = "ICICI";
			String branchName="MADHAPUR";
			String username = custDetails.getFirstname();
			String password = Util.generateRandomPassword(5);
			double amount = custDetails.getAmount();
			
			AccountDetails accDetails = new AccountDetails();
			
			accDetails.setAccountnumber(accountNo);
			accDetails.setIfsccode(ifscCode);
			accDetails.setBankname(bankName);
			accDetails.setBranchname(branchName);
			accDetails.setPannumber(panNumber);
			accDetails.setAmount(amount);
			
			CustomerCredentials custCreds = new CustomerCredentials();
			custCreds.setUsername(username);
			custCreds.setPassword(password);
			
			
			accDetails.setCustCreds(custCreds);
			
			customerRepos.save(custDetails);
			
			return customerRepos.save(accDetails);
		}
	}
	
	
	
	public AccountDetails getLogin(String username, String password) {
		CustomerCredentials creds =  accountsRepository.findByUsernameAndPassword(username, password);
		return creds.getAccountDetails();
	}
	
	
	
	public AccountDetails addBeneficrAccounts(BeneficiaryBean ben,String username, String password) {
		
		BeneficiaryAccounts benAccounts = new BeneficiaryAccounts();
		
		benAccounts.setAccountnumber(ben.getAccountnumber());
		benAccounts.setName(ben.getName());
		benAccounts.setAmount(ben.getAmount());
		benAccounts.setBeneficrpannumber(ben.getBeneficrpannumber());
		
		List<BeneficiaryAccounts> listBens = new ArrayList<BeneficiaryAccounts>();
		listBens.add(benAccounts);
		
		
		CustomerCredentials creds =  accountsRepository.findByUsernameAndPassword(username, password);
		if(creds!=null) {
			
		//	AccountDetails accountDetails = new AccountDetails();
			 AccountDetails accDetails = creds.getAccountDetails(); 
			 accDetails.setBeneficiaryAccs(listBens);
			//creds.setbAccounts(bAccounts);
			
			//accountsRepository.save(creds);
			//return creds.getAccountDetails();
			
			  return customerRepos.save(accDetails);
			 
		}else {
			throw new CustomerNotFoundException("Customer Not Existed");
		}
		
	}
	
	
	public AccountDetails customerTransaction(String username, String password,TransactionDetails transDetails) {
		String beneficrAcNo = transDetails.getBeneficiaryAccNumber();
		String productname = transDetails.getProductname();
		CustomerCredentials creds =  accountsRepository.findByUsernameAndPassword(username, password);
		AccountDetails acDetls=null;
		
		if(creds!=null) {
			List<BeneficiaryAccounts> benList=creds.getAccountDetails().getBeneficiaryAccs();
		if(benList!=null&&!benList.isEmpty()) {
			for(int i=0;i<benList.size();i++) {
				String benAcNo = benList.get(i).getAccountnumber();
				if(beneficrAcNo.equalsIgnoreCase(benAcNo)) {
					
					double accHoldAmt = creds.getAccountDetails().getAmount();
					
					log.info("Amount in the Account Holder Account::: "+accHoldAmt);
					
					double benAmt = benList.get(i).getAmount();
					
					log.info("Benificiary Account Amount::: "+benAmt);
					
					double transAmt = transDetails.getTransactAmount();
					
					log.info("Transaction Amount::: "+transAmt);
					
					double  accHoldBalanceAmt = accHoldAmt - transAmt;
					
					log.info("Account Holder balance Amt after Transaction ::: "+accHoldBalanceAmt);
					
					double benBalanceAmt = benAmt + transAmt;
					
					log.info("Beneficiary balance Amount after Transaction:: "+benBalanceAmt);
					
					acDetls = creds.getAccountDetails();
					acDetls.setAmount(accHoldBalanceAmt);
					acDetls.getBeneficiaryAccs().get(i).setAmount(benBalanceAmt);
					acDetls.getBeneficiaryAccs().get(i).setProductname(productname);
					
					acDetls =  customerRepos.save(acDetls);
					
				}else {
					throw new CustomerNotFoundException("No Beneficiary For This Customer");
				}
			}
		}else {
			throw new CustomerNotFoundException("No Beneficiary For This Customer");
		}
		}else {
			throw new CustomerNotFoundException("Customer Not Existed");
		}
		
		return acDetls;
		
		
	/*	for(int i=0; i<=bAccounts.size();i++) {
			BeneficiaryAccounts account = bAccounts.get(i);
			String panNumber = account.getAccholderpannumber();
			CustomerDetails custDetails = customerRepos.findBypannumber(panNumber);
			
			if(custDetails!=null) {
				CustomerCredentials creds =  accountsRepository.findByUsernameAndPassword(username, password);
				creds.setbAccounts(bAccounts);
				return customerRepos.save(bAccounts);
			}else {
				throw new CustomerNotFoundException("Customer Not Existed To Add Benificiary");
			}
		}*/
		
	}
		
	/*
	 * public CustomerRegistrationInfo customerReg(CustomerDetails customer) {
	 * log.info("in service "+customer.getAddress());
	 * 
	 * 
	 * //public List<String> createCustomerCredentials(customerInfo);
	 * 
	 * }
	 */
	
	/*
	private List<String> createCustomerCredentials(CustomerRegistrationInfo customerInfo) {
		
		String userName = customerInfo.getFirstname();
		String pwd =  Util.generateRandomPassword(5);
		String accountNumber = Util.generateAccountNumber();
		
		
	}
	*/
	
	/*
	public Optional<CustomerRegistrationInfo> isCustomerRegSuccess(Integer id) {
		return customerRepos.findById(id);
		
	}
	
	public List<String> getCredentialsForCustomer(String firstname) {
		CustomerRegistrationInfo customer = customerRepos.findByfirstname(firstname);
		List<String> creds = new ArrayList<String>();
		
		customer.setUsername(customer.getFirstname());
		customer.setPassword(customer.getId()+"ABCD");
		customerRepos.save(customer);
		
		creds.add(customer.getFirstname());
		creds.add(customer.getId()+"ABCD");
		
		return creds;
	}
	
	
	//public String getAccountNumber(LoginCredentials creds){
	
	public String getAccountNumber(String username, String password){
		
		//String userName = creds.getUsername();
		String accountNumber="";
		CustomerRegistrationInfo customer =customerRepos.findByusername(username);
		
		if(customer.getUsername().equals(username)) {
			accountNumber = "Successfully Registered and your Account Number is : 293847561023";
		}
		return accountNumber;
		
	}

	public String transactionMoney(String fromAccountNumber, String toAccountNumner, String amount) {
		log.debug("Trasaction in progress....");
		 
		String message="";
		
		CustomerRegistrationInfo customer = customerRepos.findByaccountnumber(fromAccountNumber);
		
		if(customer.getAccountNumber()!=null) {
			 message = "Transaction Completed Successfully"; 
		}
		else {
			message = "Transaction Failed";
		}
		
		return message;
	}
	*/
	
}

