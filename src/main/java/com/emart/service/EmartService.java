package com.emart.service;

import com.emart.accessData.Category;
import com.emart.accessData.JdbcRepository;
import com.emart.accessData.Product;
import com.emart.accessData.User;
import com.emart.session.EmartSession;
import com.emart.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmartService {

    @Autowired
    private JdbcRepository jdbcRepository;

    public JSONObject login(String username, String password) {
        JSONObject response = new JSONObject();

        List<User> resultList = (List<User>) jdbcRepository.findAllUsers();
        for (User user : resultList) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
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
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject getProductList(String category, String username) {
        JSONObject response = new JSONObject();

        List<Product> resultList = jdbcRepository.getProducts(category);
        if (resultList != null && !resultList.isEmpty()) {
            JSONArray products = new JSONArray();
            products.addAll(resultList);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "success");
            response.put("products", products);
            return response;
        }
        response.put(Constants.response_status_key, "failure");
        response.put(Constants.response_message_key, "No products found");
        return response;
    }

    public JSONObject addProduct(Product product) {
		JSONObject response = new JSONObject();
		try {
			jdbcRepository.insertProduct(product);
			response.put(Constants.response_status_key, "success");
			response.put(Constants.response_message_key, "product added");
		} catch (Exception e) {
			response.put(Constants.response_status_key, "failure");
			response.put(Constants.response_message_key, e.getMessage());
		}
		return response;
	}

    public JSONObject getCategories() {
        JSONArray categories = new JSONArray();
        categories.add(new Category("Electronics", 1, "https://image.flaticon.com/icons/png/512/2777/2777154.png"));
        categories.add(new Category("Books", 2, "https://image.flaticon.com/icons/png/512/2232/2232688.png"));
        categories.add(new Category("Clothes", 3, "https://image.flaticon.com/icons/png/512/2331/2331716.png"));
        categories.add(new Category("Groceries", 4, "https://image.flaticon.com/icons/png/512/1261/1261126.png"));
        categories.add(new Category("Outdoor", 5, "https://image.flaticon.com/icons/png/512/1752/1752764.png"));
        categories.add(new Category("Pets", 6, "https://image.flaticon.com/icons/png/512/1344/1344623.png"));


        JSONObject response = new JSONObject();
        response.put("categories", categories);
        return response;
    }

    private boolean checkUserLogin(String username) {
        List<User> users = EmartSession.getInstance().getActiveUsers();
        for (User user : users) {
            if (user.getName().equals(username)) return true;
        }
        return false;
    }
}
