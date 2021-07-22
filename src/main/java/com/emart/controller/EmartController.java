package com.emart.controller;

import com.emart.model.CartItem;
import com.emart.model.Product;
import com.emart.accessData.ProductFactory;
import com.emart.model.User;
import com.emart.payment.PaymentDetails;
import com.emart.payment.impl.CreditCard;
import com.emart.payment.impl.NetBankingDetails;
import com.emart.payment.impl.PaypalDetails;
import com.emart.service.EmartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for the network calls from the frontend
 */
@RestController
public class EmartController {

    @Autowired
    private EmartService emartService;

    /**
     * API for signup
     */
    @PostMapping(path = "/register")
    public ResponseEntity<JSONObject> registerUser(@RequestBody User user) {
        JSONObject response = emartService.register(user);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API for login
     */
    @PostMapping(path = "/login")
    public ResponseEntity<JSONObject> loginuser(@RequestBody JSONObject loginDetails) {

        String username = (String) loginDetails.get("username");
        String password = (String) loginDetails.get("password");
        JSONObject response = emartService.login(username, password);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to get categories
     */
    @GetMapping(path = "/getCategories")
    public ResponseEntity<JSONObject> getCategories(@RequestParam String username) {

        JSONObject response = emartService.getCategories();
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to get products
     */
    @GetMapping(path = "/getProducts")
    public ResponseEntity<JSONObject> getProducts(@RequestParam String username, @RequestParam String category) {

        JSONObject response = emartService.getProductList(category, username);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to add product
     */
    @PostMapping(path = "/addProduct")
    public ResponseEntity<JSONObject> addProduct(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        String category = (String) jsonObject.get("category");

        Product product = new ProductFactory().getProduct(category);
        product.setName((String) jsonObject.get("name"));
        product.setCategory(category);
        product.setPrice((double) jsonObject.get("price"));
        product.setQuantity((int) jsonObject.get("quantity"));
        product.setRating((double) jsonObject.get("rating"));
        JSONObject response = emartService.addProduct(username,product);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to add product decorator
     */
    @PostMapping(path = "/addProductDecorator")
    public ResponseEntity<JSONObject> addProductDecorator(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        String decorator = (String) jsonObject.get("decorator");
        int productId = (int) jsonObject.get("productId");
        JSONObject response = emartService.addProductDecorator(username,productId,decorator);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to remove product
     */
    @PostMapping(path = "/removeProduct")
    public ResponseEntity<JSONObject> removeProduct(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        long productId = Long.parseLong((String) jsonObject.get("productId"));
        int quantity = (int) jsonObject.get("quantity");

        JSONObject response = emartService.removeProduct(username,productId, quantity);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to change price
     */
    @PostMapping(path = "/changePrice")
    public ResponseEntity<JSONObject> changePrice(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        long productId = Long.parseLong((String) jsonObject.get("productId"));
        double price = (double) jsonObject.get("price");

        JSONObject response = emartService.changePrice(username,productId, price);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to change quantity
     */
    @PostMapping(path = "/changeQuantity")
    public ResponseEntity<JSONObject> changeQuantity(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        long productId = Long.parseLong((String) jsonObject.get("productId"));
        int quantity = (int) jsonObject.get("quantity");

        JSONObject response = emartService.changeQuantity(username,productId, quantity);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to get cart items
     */
    @GetMapping(path = "/getCart")
    public ResponseEntity<JSONObject> getCartItems(@RequestParam String username, @RequestParam Long userId) {
        JSONObject response = emartService.getCartItems(userId);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to add item to cart
     */
    @PostMapping(path = "/addToCart")
    public ResponseEntity<JSONObject> addToCart(@RequestParam String username, @RequestBody CartItem cartItem) {

        JSONObject response = emartService.addToCart(cartItem);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to remove item from cart
     */
    @PostMapping(path = "/removeFromCart")
    public ResponseEntity<JSONObject> removeFromCart(@RequestParam String username, @RequestBody CartItem cartItem) {

        JSONObject response = emartService.removeFromCart(cartItem.getUserId(), cartItem.getProductId());
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to get payment options
     */
    @GetMapping(path = "/getPayments")
    public ResponseEntity<JSONObject> getPayments(@RequestParam String username, @RequestParam Long userId) {
        JSONObject response = emartService.getPayments(userId);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to add payment option
     */
    @PostMapping(path = "/addPayment")
    public ResponseEntity<JSONObject> addPayment(@RequestParam String username, @RequestBody JSONObject jsonObject) {

        int type = (int) jsonObject.get("type");
        long userId = Long.parseLong((String) jsonObject.get("userId"));
        PaymentDetails paymentDetails;
        switch (type){
            case 0:
                paymentDetails = new PaypalDetails(userId, type);
                ((PaypalDetails)paymentDetails).setEmail((String) jsonObject.get("email"));
                ((PaypalDetails)paymentDetails).setMobileNumber((int) jsonObject.get("mobileNumber"));
                break;
            case 1:
                paymentDetails = new NetBankingDetails(userId, type);
                ((NetBankingDetails)paymentDetails).setBankName((String) jsonObject.get("bankName"));
                ((NetBankingDetails)paymentDetails).setName((String) jsonObject.get("name"));
                ((NetBankingDetails)paymentDetails).setAccountNumber((int) jsonObject.get("accountNumber"));
                break;
            default:
                paymentDetails = new CreditCard(userId, type);
                ((CreditCard)paymentDetails).setCardNumber((int) jsonObject.get("cardNumber"));
                ((CreditCard)paymentDetails).setName((String) jsonObject.get("name"));
                ((CreditCard)paymentDetails).setExpiryDate((String) jsonObject.get("expiryDate"));
                break;
        }
        JSONObject response = emartService.addPayment(paymentDetails);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    /**
     * API to make purchase
     */
    @PostMapping(path = "/makePurchase")
    public ResponseEntity<JSONObject> makePurchase(@RequestParam String username, @RequestBody JSONObject jsonObject) {
        long userId = (int) jsonObject.get("userId");
        long paymentId = (int) jsonObject.get("paymentId");
        double amount = (double) jsonObject.get("amount");
        JSONObject response = emartService.makePurchase(userId, paymentId, amount);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }
}
