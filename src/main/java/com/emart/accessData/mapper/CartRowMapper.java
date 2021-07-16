package com.emart.accessData.mapper;

import com.emart.model.CartItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<CartItem> {

    @Override
    public CartItem mapRow(ResultSet rs, int i) throws SQLException {

        CartItem cartItem = new CartItem();

        cartItem.setUserId(rs.getLong("userid"));
        cartItem.setQuantity(rs.getInt("quantity"));
        cartItem.setProductId(rs.getLong("productid"));

        return cartItem;
    }
}
