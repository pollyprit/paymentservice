package com.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    @Bean
    public RazorpayClient createRazorpayClient() {
        try {
            return new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }
}
