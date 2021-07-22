package com.emart.model.decorator;

import com.emart.model.Product;

public class DiscountDecorator extends ProductDecorator{
	
	Product product;
	double dicountFraction=0.5;

	public double getDicountFraction() {
		return dicountFraction;
	}

	public void setDicountFraction(double dicountFraction) {
		this.dicountFraction = dicountFraction;
	}

	public DiscountDecorator(Product product, double dicountFraction) {
		this.product = product;
		this.dicountFraction = dicountFraction;
	}

	public DiscountDecorator(Product product) {
		this.product = product;
	}

	@Override
	public double getPrice() {
		return 0.5*product.getPrice();
	}

}
