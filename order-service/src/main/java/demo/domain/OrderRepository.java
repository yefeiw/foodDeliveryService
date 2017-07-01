package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
/*
 * Created by yefeiw on 6/25/17.
 */

    public interface OrderRepository extends MongoRepository<Order,String> {
        Order findById(@Param("id")String id);
    }
