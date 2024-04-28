package com.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class PhonePePaymentService implements PaymentService {
    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        return null;
    }
}
