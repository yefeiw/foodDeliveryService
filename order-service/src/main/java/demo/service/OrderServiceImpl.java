package demo.service;

import demo.domain.Order;
import demo.domain.OrderItem;
import demo.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yefeiw on 6/25/17.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository repository) {
        this.orderRepository = repository;
    }

    @Override
    public void createOrder(List<OrderItem> itemList) {
        Order newOrder = new Order("TBD", "TBD", "TBD", "TBD", itemList);
        this.orderRepository.save(newOrder);
    }

    @Override
    public Order getOrder(String id) {
        return this.orderRepository.findById(id);
    }


}
