package com.bank.reigister.customer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BeneficiaryAccounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String accountnumber;
	private String name;
	//private String accholderpannumber;
	private String beneficrpannumber;
	private double amount;
	private String productname;
	
	
	
	/*
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private CustomerDetails custDetails; */
	
/*	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private AccountDetails accDetails;
	
	
	public AccountDetails getAccDetails() {
		return accDetails;
	}

	public void setAccDetails(AccountDetails accDetails) {
		this.accDetails = accDetails;
	}  */
	
	
/*	@ManyToOne(fetch = FetchType.EAGER)
	private CustomerCredentials creds;
	
		
	public CustomerCredentials getCreds() {
		return creds;
	}

	public void setCreds(CustomerCredentials creds) {
		this.creds = creds;
	} */

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public String getAccholderpannumber() {
		return accholderpannumber;
	}

	public void setAccholderpannumber(String accholderpannumber) {
		this.accholderpannumber = accholderpannumber;
	} */

	public String getBeneficrpannumber() {
		return beneficrpannumber;
	}

	public void setBeneficrpannumber(String beneficrpannumber) {
		this.beneficrpannumber = beneficrpannumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/*
	public CustomerDetails getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(CustomerDetails custDetails) {
		this.custDetails = custDetails;
	}*/
	
	
	
}
