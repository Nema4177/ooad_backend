package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class CreditCard implements PaymentDetails{
	
	private String cardHoldername;
	private int cardNumber;
	private String expiryDate;
	private int userId;
		
	public CreditCard(int userId,String cardHoldername, int cardNumber, String expiryDate) {
		this.userId = userId;
		this.cardHoldername = cardHoldername;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}
	
	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
