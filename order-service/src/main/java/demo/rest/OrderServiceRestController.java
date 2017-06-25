package demo.rest;

import demo.domain.Order;
import demo.service.OrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yefeiw on 6/25/17.
 */
@RestController
public class OrderServiceRestController {
    private final String defaultPageNum = "0";
    private final String defaultPageSize = "2";
    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderServiceRestController.class);

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    Page<Order> findAll(
            @RequestParam(name = "page", required = false, defaultValue = defaultPageNum) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) Integer size) {
           return this.orderService.findAll(new PageRequest(page,size));
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    Order findById(@PathVariable(value = "id") String id) {
        return orderService.findById(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    List<Order> CreateOrders(@RequestBody  List<Order> payload) {
       this.orderService.createOrder(payload);
    }

    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    void purge() {
        this.orderService.deleteAll();
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    void deleteByid(@PathVariable(value = "id") String id) {
        orderService.deleteById(id);
    }

    @RequestMapping(value = "order/{id}", method = RequestMethod.PUT)
    void modifyByid(@PathVariable(value = "id")String id, @RequestBody int status) {
        Order order = this.orderService.getOrder(id);
        order.setStatus(status);
        this.orderService.saveById(id);
    }

}
