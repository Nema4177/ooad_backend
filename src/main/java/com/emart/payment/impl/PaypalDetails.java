package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class PaypalDetails extends PaymentDetails{

	private int mobileNumber;
	private String email;

	public PaypalDetails() {
	}

	public PaypalDetails(long paymentId, long userId, int type) {
		super(paymentId, userId, type);
	}

	public PaypalDetails(int mobileNumber, String email) {
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
