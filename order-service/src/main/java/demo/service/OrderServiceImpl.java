package demo.service;

import demo.domain.Order;
import demo.domain.OrderItem;
import demo.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }
    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }
    @Override
    public void deleteById(String id) {
        orderRepository.delete(id);
    }
    @Override
    public void save(Order order) {
        this.orderRepository.save(order);
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
