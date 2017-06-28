package demo.service;

import demo.domain.Order;
import demo.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yefeiw on 6/25/17.
 */
public interface OrderService {
    Order getOrder(String id);
    Page<Order> findAll(Pageable pageable);
    Order findById(String id);
    void deleteAll();
    void deleteById(String id);
    void save(Order order);
    void createOrder(List<OrderItem> order);
}
