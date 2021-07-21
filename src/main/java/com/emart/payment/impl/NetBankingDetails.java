package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class NetBankingDetails extends PaymentDetails{

	private String bankName;
	private long accountNumber;
	private String username;

	public NetBankingDetails(long paymentId, long userId, int type) {
		super(paymentId, userId, type);
	}

	public NetBankingDetails(String bankName, long accountNumber, String username) {
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.username = username;
	}

	public NetBankingDetails() {
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
