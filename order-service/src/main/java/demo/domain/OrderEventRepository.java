package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by yefeiw on 6/30/17.
 */
public interface OrderEventRepository extends MongoRepository<OrderEvent, String> {
    List<OrderEvent> findOrderEventById(String orderId);
}
