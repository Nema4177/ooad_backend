package com.emart.controller;

import com.emart.accessData.Product;
import com.emart.accessData.User;
import com.emart.service.EmartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmartController {

    //	@Autowired // This means to get the bean called userRepository Which is auto-generated by Spring, we will use it to handle the data
//	private UserRepository userRepository;
//	
    @Autowired
    private EmartService emartService;

    @PostMapping(path = "/register")
    public ResponseEntity<JSONObject> registerUser(@RequestBody User user) {
        JSONObject response = emartService.register(user);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JSONObject> loginuser(@RequestBody JSONObject loginDetails) {

        String username = (String) loginDetails.get("username");
        String password = (String) loginDetails.get("password");
        JSONObject response = emartService.login(username, password);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getCategories")
    public ResponseEntity<JSONObject> getCategories(@RequestParam String username) {

        JSONObject response = emartService.getCategories();
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getProducts")
    public ResponseEntity<JSONObject> getProducts(@RequestParam String username, @RequestParam String category) {

        JSONObject response = emartService.getProductList(category, username);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/addProduct")
    public ResponseEntity<JSONObject> addProduct(@RequestBody Product product) {

        JSONObject response = emartService.addProduct(product);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

}
