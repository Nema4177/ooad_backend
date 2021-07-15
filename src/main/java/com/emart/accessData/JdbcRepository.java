package com.emart.accessData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emart.accessData.mapper.UserRowMapper;

@Repository
public class JdbcRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> findAllUsers() {
	    
        String sql = "SELECT * FROM user_emart";
        List<User> users = jdbcTemplate.query(sql,new UserRowMapper());
        return users;
        
    }
	
	
	public void insertUser(User user){
        
        String sql = "INSERT INTO user_emart (username, email, password, type,location) VALUES (?, ?, ?,?,?)";
                                 
        jdbcTemplate.update(sql, new Object[] { user.getName(),user.getEmail(),user.getPassword(),user.getType(),user.getLocation()});
                
    }
}
