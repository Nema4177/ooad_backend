package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class NetBankingDetails extends PaymentDetails{

	private String bankName;
	private long accountNumber;
	private String name;

	public NetBankingDetails(long paymentId, long userId, int type) {
		super(paymentId, userId, type);
	}

	public NetBankingDetails(String bankName, long accountNumber, String name) {
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.name = name;
	}

	public NetBankingDetails(long userId, int type) {
		super(userId, type);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
