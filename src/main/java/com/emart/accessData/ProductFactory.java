package com.emart.accessData;

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
            return new ClothProduct();
        } else if (category.equalsIgnoreCase("Outdoor")) {
            return new ClothProduct();
        } else if (category.equalsIgnoreCase("Pets")) {
            return new ClothProduct();
        } else {
            return null;
        }
    }
}
