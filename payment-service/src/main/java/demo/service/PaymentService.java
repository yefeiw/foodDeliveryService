package demo.service;

import demo.domain.CardInfo;
import demo.domain.Invoice;
import demo.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    Payment getPaymentById(String id);
    Payment getPaymentByOrderId(String orderId);
    Page<Payment> findAll(Pageable pageable);
    void deleteAll();
    Payment createPayment(Invoice invoice);
    void fulfillPayment(Payment payment, CardInfo info);

}
