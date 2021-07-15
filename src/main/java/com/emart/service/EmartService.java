package com.emart.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emart.accessData.JdbcRepository;
import com.emart.accessData.User;
import com.emart.utils.Constants;

import com.emart.session.*;

@Service
public class EmartService {
		
	@Autowired
	private JdbcRepository jdbcRepository;
	
	public JSONObject login(String username, String password) {
		JSONObject response = new JSONObject();
		
		List<User> resultList = (List<User>) jdbcRepository.findAllUsers();
		for(User user: resultList) {
			if(user.getName().equals(username) && user.getPassword().equals(password)) {
				EmartSession.getInstance().getActiveUsers().add(user);
				response.put(Constants.response_status_key, "success");
				response.put(Constants.response_message_key, "success");
				response.put("userId", user.getUserId());
				response.put("username", user.getName());
				response.put("type", user.getType());
				response.put("location", user.getLocation());
				response.put("email", user.getEmail());
				return response;
			}
		}
		response.put(Constants.response_status_key, "failure");
		response.put(Constants.response_message_key, "User with mentioned details not found");
		return response;		
	}
	
	public JSONObject register(User user) {
		JSONObject response = new JSONObject();
		
		try {
			jdbcRepository.insertUser(user);
			response.put(Constants.response_status_key, "success");
			response.put(Constants.response_message_key, "success");
			response.put("username", user.getName());
		}catch(Exception e) {
			response.put(Constants.response_status_key, "failure");
			response.put(Constants.response_message_key, e.getMessage());
		}
		return response;
	}

	public JSONObject getProductList(String category, String userID) {
		JSONObject response = new JSONObject();

		try {
			response.put(Constants.response_status_key, "success");

		}catch(Exception e) {
			response.put(Constants.response_status_key, "failure");
			response.put(Constants.response_message_key, e.getMessage());
		}
		return response;
	}

	public JSONObject searchForProduct(String category, String userID, String keyword) {
		JSONObject response = new JSONObject();

		try {
			response.put(Constants.response_status_key, "success");

		}catch(Exception e) {
			response.put(Constants.response_status_key, "failure");
			response.put(Constants.response_message_key, e.getMessage());
		}
		return response;
	}

	public JSONObject getCategories() {
		JSONArray categories = new JSONArray();
		categories.add("Electronics");
		categories.add("Books");
		categories.add("Clothes");
		categories.add("Groceries");
		categories.add("Outdoor");
		categories.add("Pets");

		
		JSONObject response = new JSONObject();
		response.put("categories", categories);
		return response;
	}
	
	private boolean checkUserLogin(String username) {
		List<User> users = EmartSession.getInstance().getActiveUsers();
		for(User user: users) {
			if(user.getName().equals(username)) return true;
		}
		return false;
	}
}
