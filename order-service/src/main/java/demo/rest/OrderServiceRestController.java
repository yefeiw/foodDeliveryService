package demo.rest;

import demo.domain.Order;
import demo.domain.OrderEvent;
import demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    HttpStatus CreateOrder(@RequestBody  Order payload) {
        this.orderService.createOrder(payload);
            return HttpStatus.OK;
    }

    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    void purge() {
        this.orderService.deleteAll();
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    void deleteByid(@PathVariable(value = "id") String id) {
        orderService.deleteById(id);
    }

    @RequestMapping(value = "/order/event", method = RequestMethod.POST)
    public ResponseEntity processOrderEvent(@RequestBody OrderEvent orderEvent) {
        orderService.processOrderEvent(orderEvent);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
