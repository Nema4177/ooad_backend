package com.emart.accessData.mapper;



import org.springframework.jdbc.core.RowMapper;

import com.emart.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();     
        
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setType(rs.getInt("type"));
        user.setLocation(rs.getString("location"));
        user.setUserId(rs.getLong("userId"));
        
        return user;

    }
}