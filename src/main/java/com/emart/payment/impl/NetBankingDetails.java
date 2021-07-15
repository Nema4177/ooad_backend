package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class NetBankingDetails implements PaymentDetails{
	
	private int netBankingId;
	private int userId;
	private String bankName;
	private int accountNumber;
	private String password;
	private String username;
	

	public NetBankingDetails(int netBankingId, int userId, String bankName, int accountNumber, String password,
			String username) {
		super();
		this.netBankingId = netBankingId;
		this.userId = userId;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.password = password;
		this.username = username;
	}


	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
