package com.bank.reigister.customer.dtos;

import java.beans.JavaBean;

@JavaBean
public class BeneficiaryBean {
	
	private String accountnumber;
	
	private String name;
	//private String accholderpannumber;
	
	private String beneficrpannumber;
	
	private double amount;
	
	private String username;
	
	private String password;
	
	
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
	
	
	
}
