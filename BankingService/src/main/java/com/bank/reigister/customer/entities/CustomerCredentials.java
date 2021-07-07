package com.bank.reigister.customer.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CustomerCredentials {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String username;
	private String password;
	
	
	@JsonBackReference
	@OneToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "custCreds")
	private AccountDetails accountDetails;
	
	/*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "creds")
	private List<BeneficiaryAccounts> bAccounts;
	
	
	public List<BeneficiaryAccounts> getbAccounts() {
		return bAccounts;
	}

	public void setbAccounts(List<BeneficiaryAccounts> bAccounts) {
		this.bAccounts = bAccounts;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
	
	
	
}
