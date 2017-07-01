package demo.service;

import demo.domain.Payment;

public interface PaymentService {
    Payment getPaymentById(String id);

    String createPayment(Payment payment);

}
