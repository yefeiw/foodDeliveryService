package demo.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Result;
import java.util.*;

/**
 * Created by yefeiw on 6/25/17.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String PAYMENT_URL = "http://localhost:9002/payment";
    @Autowired
    private OrderRepository orderRepository;
    private OrderEventRepository orderEventRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
        this.orderRepository = orderRepository;
    }
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
    public void createOrder(Order order) {

        //step2. Start paying the order
        HttpEntity<Invoice> request = new HttpEntity<>(new Invoice(order.getId(),order.getTotal()));

        System.out.println("creating invoice for order "+order.getId()+" of total price "+order.getTotal());
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String,String>();
        vars.put("orderID",order.getId());
        try {

            Invoice comm = restTemplate.postForObject(PAYMENT_URL, request, Invoice.class);
            assert(comm != null);
            assert(comm.getOrderId().equals(order.getId()));
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        this.orderRepository.save(order);
    }

    @Override
    public Order getOrder(String id) {
        return this.orderRepository.findById(id);
    }
    //This is reserved for future enhancement
    public boolean processOrderEvent(OrderEvent orderEvent) {
        System.out.println("Processing order event "+orderEvent.getOrderId() +" with status "+orderEvent.getType());
        Order order = this.orderRepository.findById(orderEvent.getOrderId());
        if (order == null || order.getStatus() != Order.OrderStatus.PENDING) {
            System.out.println("order not found or already processed, discarding request");
            return false;
        }
        OrderEventType result = orderEvent.getType();
        if (result == OrderEventType.SUCCEED) {
            System.out.println("Payment succeeded");
            order.setStatus(Order.OrderStatus.APPROVED);
            //Set delivery time
            order.setDeliveryTimeMinute(new Random().nextInt(60));
        } else {
            System.out.println("Order failed with status "+result);
            order.setStatus(Order.OrderStatus.REJECTED);
        }
        //save to repo to preserve effects.
        orderRepository.save(order);
        return true;
    }

}
