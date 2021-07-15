package com.emart.accessData;

import com.emart.accessData.mapper.ProductRowMapper;
import com.emart.accessData.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAllUsers() {

        String sql = "SELECT * FROM user_emart";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
        return users;

    }


    public void insertUser(User user) {

        String sql = "INSERT INTO user_emart (username, email, password, type,location) VALUES (?, ?, ?,?,?)";

        jdbcTemplate.update(sql, new Object[]{user.getName(), user.getEmail(), user.getPassword(), user.getType(), user.getLocation()});

    }

    public List<Product> getProducts(String category) {
        String sql = "SELECT * FROM product_emart WHERE category =\'" + category + "\'";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper());
        return products;
    }

    public void insertProduct(Product product) {

        String sql = "INSERT INTO product_emart (name, category, imgUrl, rating, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getImgUrl(),
                product.getRating(), product.getPrice(), product.getQuantity());

    }
}
