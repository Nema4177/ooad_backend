package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class CreditCard extends PaymentDetails{
	
	private String cardHoldername;
	private long cardNumber;
	private String expiryDate;
		
	public CreditCard(String cardHoldername, long cardNumber, String expiryDate) {
		this.cardHoldername = cardHoldername;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

	public CreditCard(long userId, int type) {
		super(userId, type);
	}

	public CreditCard(long paymentId, long userId, int type) {
		super(paymentId, userId, type);
	}

	public CreditCard() {
	}

	public String getCardHoldername() {
		return cardHoldername;
	}

	public void setCardHoldername(String cardHoldername) {
		this.cardHoldername = cardHoldername;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
