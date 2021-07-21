package com.emart.accessData.mapper;

import com.emart.payment.PaymentDetails;
import com.emart.payment.impl.CreditCard;
import com.emart.payment.impl.NetBankingDetails;
import com.emart.payment.impl.PaypalDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<PaymentDetails> {

    @Override
    public PaymentDetails mapRow(ResultSet rs, int i) throws SQLException {

        int type = rs.getInt("type");
        long userId = rs.getLong("userid");
        long paymentId = rs.getLong("paymentid");
        PaymentDetails paymentDetails;
        switch (type){
            case 0:
                paymentDetails = new PaypalDetails(paymentId, userId, type);
                ((PaypalDetails)paymentDetails).setEmail(rs.getString("email"));
                ((PaypalDetails)paymentDetails).setMobileNumber(rs.getInt("mobile"));
                break;
            case 1:
                paymentDetails = new NetBankingDetails(paymentId, userId, type);
                ((NetBankingDetails)paymentDetails).setBankName(rs.getString("bank"));
                ((NetBankingDetails)paymentDetails).setName(rs.getString("name"));
                ((NetBankingDetails)paymentDetails).setAccountNumber(rs.getLong("account"));
                break;
            default:
                paymentDetails = new CreditCard(paymentId, userId, type);
                ((CreditCard)paymentDetails).setCardNumber(rs.getLong("card"));
                ((CreditCard)paymentDetails).setName(rs.getString("name"));
                ((CreditCard)paymentDetails).setExpiryDate(rs.getString("expiry"));
                break;
        }
        return paymentDetails;
    }
}