package com.emart.payment;

public abstract class PaymentDetails {

	private long paymentId;
	private long userId;
	private int type;

	public PaymentDetails() {
	}

	public PaymentDetails(long paymentId, long userId, int type) {
		this.paymentId = paymentId;
		this.userId = userId;
		this.type = type;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public abstract boolean makePurchase(Double amount);
	
}
