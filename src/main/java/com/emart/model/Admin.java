package com.emart.model;

import com.emart.accessData.JdbcRepository;

/**
 * To demonstrate the singleton pattern
 */
public class Admin {
	
	public static Admin admin = new Admin();
	private JdbcRepository jdbcRepository = new JdbcRepository();
	
	private Admin() {
		
	}
	
	public static Admin getInstance() {
		return admin;
	}

	public void addProduct(Product product) {
		jdbcRepository.insertProduct(product);		
	}
	
	public void updateProduct(int productId,Product product) {
		jdbcRepository.updateProduct(productId,product);		
	}
	
	public void removeProduct(Long productId, int quantity) {
        jdbcRepository.removeProduct(productId, quantity);
	}
	
	public void changeQuantity(Long productId, int quantity) {
        jdbcRepository.changeQuantity(productId, quantity);
	}
	
	public void changePrice(Long productId, double price) {
        jdbcRepository.changePrice(productId, price);
	}

}
