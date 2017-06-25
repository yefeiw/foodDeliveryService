package demo.service;

import demo.domain.Order;
import demo.domain.OrderItem;

import java.util.List;

/**
 * Created by yefeiw on 6/25/17.
 */
public interface OrderService {
    Order getOrder(String id);
    void createOrder(List<OrderItem> order);
}
