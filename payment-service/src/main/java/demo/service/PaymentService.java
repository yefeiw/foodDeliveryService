package demo.service;

import demo.domain.Invoice;
import demo.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    Payment getPaymentById(String id);
    Page<Payment> findAll(Pageable pageable);
    String createPayment(Invoice invoice);

}
