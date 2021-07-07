package com.bank.reigister.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.reigister.customer.entities.CustomerCredentials;


@Repository
public interface AccountDetailsRepository extends JpaRepository<CustomerCredentials, Integer> {
	
	CustomerCredentials  findByUsernameAndPassword(String username,String password);
}
