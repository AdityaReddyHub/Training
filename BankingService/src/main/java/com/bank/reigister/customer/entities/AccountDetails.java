package com.bank.reigister.customer.entities;

import java.math.BigDecimal;
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
public class AccountDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String accountnumber;
	private String bankname;
	private String branchname;
	private String ifsccode;
	private String pannumber;
	private double amount;
	
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private CustomerCredentials custCreds;
	
	
	@OneToMany(targetEntity = BeneficiaryAccounts.class,cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<BeneficiaryAccounts> beneficiaryAccs;
	
	
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy =
	 * "accountDetails") private CustomerDetails customerDetails;
	 */
	
	
	public List<BeneficiaryAccounts> getBeneficiaryAccs() {
		//return custCreds.getbAccounts();
		return beneficiaryAccs;
	}
	public void setBeneficiaryAccs(List<BeneficiaryAccounts> beneficiaryAccs) {
		this.beneficiaryAccs = beneficiaryAccs;
	}
	
	
	public CustomerCredentials getCustCreds() {
		return custCreds;
	}
	public void setCustCreds(CustomerCredentials custCreds) {
		this.custCreds = custCreds;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	/*
	 * public CustomerDetails getCustomerDetails() { return customerDetails; }
	 * public void setCustomerDetails(CustomerDetails customerDetails) {
	 * this.customerDetails = customerDetails; }
	 */
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	

}
