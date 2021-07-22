package com.emart.service;

import com.emart.model.*;
import com.emart.model.decorator.DiscountDecorator;
import com.emart.model.decorator.GiftwrapDecorator;
import com.emart.model.decorator.ProductDecorator;
import com.emart.accessData.JdbcRepository;
import com.emart.payment.PaymentDetails;
import com.emart.session.EmartSession;
import com.emart.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to connect controller and JDBC repo
 */
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
                response.put(Constants.response_message_key, "login success");
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
            response.put(Constants.response_message_key, "register success");
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

    public JSONObject addProduct(String username,Product product) {
		JSONObject response = new JSONObject();
		try {
			if(username.equalsIgnoreCase("admin")){
				Admin.getInstance().addProduct(product);
				response.put(Constants.response_status_key, "success");
				response.put(Constants.response_message_key, "product added");
			}else {
				response.put(Constants.response_status_key, "failure");
				response.put(Constants.response_message_key, "Only admin is allowed to add a product");
			}
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

    public JSONObject removeProduct(String username,Long productId, int quantity) {
        JSONObject response = new JSONObject();
        try {
        	if(username.equalsIgnoreCase("admin")){
				Admin.getInstance().removeProduct(productId,quantity);
				response.put(Constants.response_status_key, "success");
				response.put(Constants.response_message_key, "product added");
			}else {
				response.put(Constants.response_status_key, "failure");
				response.put(Constants.response_message_key, "Only admin is allowed to remove a product");
			}
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject changePrice(String username,Long productId, double price) {
        JSONObject response = new JSONObject();
        try {
        	if(username.equalsIgnoreCase("admin")){
				Admin.getInstance().changePrice(productId,price);
				response.put(Constants.response_status_key, "success");
				response.put(Constants.response_message_key, "product added");
			}else {
				response.put(Constants.response_status_key, "failure");
				response.put(Constants.response_message_key, "Only admin is allowed to modify a product");
			};
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject changeQuantity(String username,Long productId, int quantity) {
        JSONObject response = new JSONObject();
        try {
        	if(username.equalsIgnoreCase("admin")){
				Admin.getInstance().changeQuantity(productId,quantity);
				response.put(Constants.response_status_key, "success");
				response.put(Constants.response_message_key, "product added");
			}else {
				response.put(Constants.response_status_key, "failure");
				response.put(Constants.response_message_key, "Only admin is allowed to modify a product");
			};
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject getCartItems(Long userId) {
        JSONObject response = new JSONObject();
        try {
            JSONArray cartItems = new JSONArray();
            List<CompleteCartItem> resultList = jdbcRepository.getCartItems(userId);
            cartItems.addAll(resultList);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "success");
            response.put("cartItems", cartItems);
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject addToCart(CartItem cartItem) {
        JSONObject response = new JSONObject();
        try {
            jdbcRepository.addToCart(cartItem);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "product added");
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject removeFromCart(Long userId, Long productId) {
        JSONObject response = new JSONObject();
        try {
            jdbcRepository.removeFromCart(userId, productId);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "product updated");
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject getPayments(Long userId) {
        JSONObject response = new JSONObject();
        try {
            JSONArray result = new JSONArray();
            List<PaymentDetails> resultList = jdbcRepository.getPayments(userId);
            result.addAll(resultList);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "success");
            response.put("payments", result);
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject addPayment(PaymentDetails paymentDetails) {
        JSONObject response = new JSONObject();
        try {
            jdbcRepository.addPayment(paymentDetails);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "payment added");
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    public JSONObject makePurchase(long userId, long paymentId, double amount) {
        JSONObject response = new JSONObject();
        try {
            jdbcRepository.makePurchase(userId);
            response.put(Constants.response_status_key, "success");
            response.put(Constants.response_message_key, "Items bought");
        } catch (Exception e) {
            response.put(Constants.response_status_key, "failure");
            response.put(Constants.response_message_key, e.getMessage());
        }
        return response;
    }

    private boolean checkUserLogin(String username) {
        List<User> users = EmartSession.getInstance().getActiveUsers();
        for (User user : users) {
            if (user.getName().equals(username)) return true;
        }
        return false;
    }

	public JSONObject addProductDecorator(String username,int productId, String decorator) {
		Product product = jdbcRepository.getProduct(productId);
        JSONObject response = new JSONObject();
		Product productDecorator=null;
		if(decorator.equals("discount")) {
			productDecorator = new DiscountDecorator(product);
		}else if(decorator.equals("gift")) {
			productDecorator = new GiftwrapDecorator(product);
		}
		if(productDecorator!=null)
		{
			if(username.equalsIgnoreCase("admin")) {
				Admin.getInstance().updateProduct(productId, productDecorator);
				response.put(Constants.response_status_key, "success");
	            response.put(Constants.response_message_key, "Decorator applied successfully");
			}else {
				response.put(Constants.response_status_key, "failure");
				response.put(Constants.response_message_key, "Only admin is allowed to modify a product");
			}
		}
		else {
			response.put(Constants.response_status_key, "failure");
	        response.put(Constants.response_message_key, "No matching decorator");
		}
		return response;
	}
}
