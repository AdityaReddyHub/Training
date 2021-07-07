package com.bank.reigister.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.reigister.customer.entities.AccountDetails;
import com.bank.reigister.customer.entities.BeneficiaryAccounts;
import com.bank.reigister.customer.entities.CustomerDetails;


@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerDetails, Integer> {
		
	//CustomerRegistrationInfo findByfirstname(String name);
	//CustomerRegistrationInfo findByusername(String username);
	//CustomerRegistrationInfo findByaccountnumber(String fromAccountNumber);
	
	CustomerDetails findBypannumber(String pannumber);
  
	AccountDetails save(AccountDetails accDetails);

	BeneficiaryAccounts save(BeneficiaryAccounts bAccounts);
	
}

