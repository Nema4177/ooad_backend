package com.emart.payment.impl;

import com.emart.payment.PaymentDetails;

public class PaypalDetails implements PaymentDetails{

	private int paypalId;
	private int userId;
	private int mobileNumber;	
	private int email;
	
	public PaypalDetails(int paypalId, int userId, int mobileNumber, int email) {
		super();
		this.paypalId = paypalId;
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	@Override
	public boolean makePurchase(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
