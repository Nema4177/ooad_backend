package com.emart.accessData;

import com.emart.model.*;

import java.awt.print.Book;

/**
 * To demonstrate the Factory pattern
 */
public class ProductFactory {

    public Product getProduct(String category) {
        if (category == null) {
            return null;
        } else if (category.equalsIgnoreCase("Electronics")) {
            return new ElectronicProduct();
        } else if (category.equalsIgnoreCase("Groceries")) {
            return new GroceriesProduct();
        } else if (category.equalsIgnoreCase("Clothes")) {
            return new ClothProduct();
        } else if (category.equalsIgnoreCase("Books")) {
            return new BookProduct();
        } else if (category.equalsIgnoreCase("Outdoor")) {
            return new OutdoorProduct();
        } else if (category.equalsIgnoreCase("Pets")) {
            return new PetProducts();
        } else {
            return null;
        }
    }
}
