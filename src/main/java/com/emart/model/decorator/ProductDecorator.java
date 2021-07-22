package com.emart.model.decorator;

import com.emart.model.Product;

/**
 * To demonstrate the decorator pattern
 */
public abstract class ProductDecorator extends Product{
	
	public abstract double getPrice();

}
