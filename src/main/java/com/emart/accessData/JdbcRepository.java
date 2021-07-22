package com.emart.accessData;

import com.emart.accessData.mapper.CartRowMapper;
import com.emart.accessData.mapper.PaymentRowMapper;
import com.emart.accessData.mapper.ProductRowMapper;
import com.emart.accessData.mapper.UserRowMapper;
import com.emart.model.CartItem;
import com.emart.model.CompleteCartItem;
import com.emart.model.Product;
import com.emart.model.User;
import com.emart.payment.PaymentDetails;
import com.emart.payment.impl.CreditCard;
import com.emart.payment.impl.NetBankingDetails;
import com.emart.payment.impl.PaypalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public void removeProduct(Long productId, int quantity) {

        String sql = "SELECT * FROM product_emart WHERE productid ="+productId;
        List<Product> product = jdbcTemplate.query(sql, new ProductRowMapper());
        String sql1;
        if (!product.isEmpty()) {
            if (product.get(0).getQuantity() <= quantity) {
                sql1 = "DELETE FROM product_emart WHERE productid ="+productId;
                jdbcTemplate.update(sql1);
            } else {
                changeQuantity(productId, quantity);
            }
        }
    }

    public void changePrice(Long productId, double price) {

        String sql = "UPDATE product_emart SET price="+price+" WHERE productid ="+productId;

        jdbcTemplate.update(sql);

    }

    public void changeQuantity(Long productId, int quantity) {

        String sql = "UPDATE product_emart SET quantity="+quantity+" WHERE productid ="+productId;

        jdbcTemplate.update(sql);

    }

    public List<CompleteCartItem> getCartItems(Long userId) {
        String sql = "Select * FROM cart_emart WHERE userid="+userId;
        List<CartItem> cartItems = jdbcTemplate.query(sql, new CartRowMapper());
        List<CompleteCartItem> resultItems = new ArrayList<>();
        for(CartItem item: cartItems) {
            String sql1 = "Select * FROM product_emart WHERE productid="+item.getProductId();
            List<Product> product = jdbcTemplate.query(sql1, new ProductRowMapper());
            resultItems.add(new CompleteCartItem(
                    item.getProductId(),
                    item.getQuantity(),
                    product.get(0).getPrice() * item.getQuantity(),
                    product.get(0).getName(),
                    product.get(0).getCategory(),
                    product.get(0).getImgUrl()
            ));
        }
        return resultItems;
    }

    public void addToCart(CartItem cartItem) {
        String sql = "SELECT * FROM cart_emart WHERE userid="+cartItem.getUserId()+" AND productid="+cartItem.getProductId();
        List<CartItem> cartItems = jdbcTemplate.query(sql, new CartRowMapper());
        String sql1;
        if (!cartItems.isEmpty()) {
            sql1 = "UPDATE cart_emart SET quantity="+(cartItem.getQuantity() + cartItems.get(0).getQuantity())+
                    " WHERE productid ="+cartItem.getProductId();
            jdbcTemplate.update(sql1);
        } else {
            sql1 = "INSERT INTO cart_emart (userid, productid, quantity) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql1, cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
        }

    }

    public void removeFromCart(Long userId, Long productId) {

        String sql = "DELETE FROM cart_emart WHERE userid="+userId+" AND productid="+productId;

        jdbcTemplate.update(sql);

    }

    public void clearCart(Long userId) {

        String sql = "DELETE FROM cart_emart WHERE userid="+userId;

        jdbcTemplate.update(sql);

    }

    public List<PaymentDetails> getPayments(Long userId) {
        String sql = "Select * FROM payment_emart WHERE userid="+userId;
        List<PaymentDetails> resultItems = jdbcTemplate.query(sql, new PaymentRowMapper());
        return resultItems;
    }

    public void addPayment(PaymentDetails paymentDetails) {
        switch (paymentDetails.getType()) {
            case 0:
                String sql0 = "INSERT INTO payment_emart (userid, type, email, mobile) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(sql0, paymentDetails.getUserId(), paymentDetails.getType(),
                        ((PaypalDetails)paymentDetails).getEmail(), ((PaypalDetails)paymentDetails).getMobileNumber());
                break;
            case 1:
                String sql1 = "INSERT INTO payment_emart (userid, type, name, account, bank) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql1, paymentDetails.getUserId(), paymentDetails.getType(),
                        ((NetBankingDetails)paymentDetails).getName(),
                        ((NetBankingDetails)paymentDetails).getAccountNumber(),
                        ((NetBankingDetails)paymentDetails).getBankName());
                break;
            case 2:
                String sql2 = "INSERT INTO payment_emart (userid, type, name, card, expiry) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql2, paymentDetails.getUserId(), paymentDetails.getType(),
                        ((CreditCard)paymentDetails).getName(),
                        ((CreditCard)paymentDetails).getCardNumber(),
                        ((CreditCard)paymentDetails).getExpiryDate());
                break;
        }
    }

    public void makePurchase(long userId) {

        clearCart(userId);
    }
}
