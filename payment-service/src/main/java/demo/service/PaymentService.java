package demo.service;

import demo.domain.Payment;

import java.util.List;

/**
 * Created by yefeiw on 6/25/17.
 */
public interface PaymentService {
    Payment getOrder(String id);
    void createOrder(List<OrderItem> order);
}
