package com.paymentservice.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Ref: https://github.com/razorpay/razorpay-java

@Service
@Primary
public class RazorPayPaymentService implements PaymentService {
    private RazorpayClient razorpayClient;

    public RazorPayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(String orderId) {
        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount",1000); // 10.00
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        // paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 30 * 60 * 1000); // 30 mins
        paymentLinkRequest.put("reference_id", orderId);
        paymentLinkRequest.put("description","Payment for order id: " + orderId);

        // TODO: Get customer info from the order-service with order id.
        JSONObject customer = new JSONObject();
        customer.put("name","+919999999999");
        customer.put("contact","Gaurav Kumar");
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("Order details", "1. Laptop");   // Get it from order
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://google.com/"); // should be our url
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = null;
        try {
            payment = razorpayClient.paymentLink.create(paymentLinkRequest);

            return payment.get("short_url");
        } catch (RazorpayException e) {
            //Get the url from dB
            return "";
        }
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        // check if my db is having the status
        // If not, call payment gateway and update my db with the status
        // return that status
        return null;
    }
}
