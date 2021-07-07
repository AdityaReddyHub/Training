package com.bank.reigister.customer.dtos;

public class TransactionDetails {
	
	private String beneficiaryAccNumber;
	private int transactAmount;
	
	public String getBeneficiaryAccNumber() {
		return beneficiaryAccNumber;
	}
	public void setBeneficiaryAccNumber(String beneficiaryAccNumber) {
		this.beneficiaryAccNumber = beneficiaryAccNumber;
	}
	public int getTransactAmount() {
		return transactAmount;
	}
	public void setTransactAmount(int transactAmount) {
		this.transactAmount = transactAmount;
	}
	
	
}
