package com.emart.accessData.mapper;



import com.emart.accessData.ProductFactory;
import org.springframework.jdbc.core.RowMapper;

import com.emart.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper class for product table
 */
public class ProductRowMapper implements RowMapper<Product> {

    private final ProductFactory productFactory = new ProductFactory();

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = productFactory.getProduct(rs.getString("category"));
        
        product.setCategory(rs.getString("category"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setProductId(rs.getLong("productid"));
        product.setRating(rs.getDouble("rating"));
       
        return product;

    }
}