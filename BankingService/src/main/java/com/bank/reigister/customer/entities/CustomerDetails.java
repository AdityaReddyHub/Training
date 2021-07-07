package com.bank.reigister.customer.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class CustomerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	private String firstname;
	private String lastname;
	private double amount;
	
	@NotBlank(message = "PanNumber is mandatory")
	private String pannumber;
	
	private Date dob;
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	
	
	public CustomerDetails(){
		
	}
	
	public CustomerDetails(String pannumber){
		this.pannumber=pannumber;
	}
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) private
	 * AccountDetails accountDetails;
	 */
	
/*	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "custDetails")
	private List<BeneficiaryAccounts> beneficrAccounts;
	
	
	public List<BeneficiaryAccounts> getBeneficrAccounts() {
		return beneficrAccounts;
	}
	public void setBeneficrAccounts(List<BeneficiaryAccounts> beneficrAccounts) {
		this.beneficrAccounts = beneficrAccounts;
	} */
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/*
	 * public AccountDetails getAccountDetails() { return accountDetails; } public
	 * void setAccountDetails(AccountDetails accountDetails) { this.accountDetails =
	 * accountDetails; }
	 */

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
