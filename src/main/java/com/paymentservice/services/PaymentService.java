package com.paymentservice.services;

public interface PaymentService {
    public String createPaymentLink(String orderId);

    public String getPaymentStatus(String paymentId);
}
