package com.emart.model.decorator;

import com.emart.model.Product;

/**
 * To demonstrate the decorator pattern and add gift wrapping ot the product
 */
public class GiftwrapDecorator extends ProductDecorator{
	
	Product product;
	double giftwrapCost;
	
	public double getGiftwrapCost() {
		return giftwrapCost;
	}

	public void setGiftwrapCost(double giftwrapCost) {
		this.giftwrapCost = giftwrapCost;
	}

	public GiftwrapDecorator(Product product) {
		this.product = product;
	}

	public GiftwrapDecorator(Product product, double giftwrapCost) {
		this.product = product;
		this.giftwrapCost = giftwrapCost;
	}

	@Override
	public double getPrice() {
		return product.getPrice()+giftwrapCost;
	}

}
