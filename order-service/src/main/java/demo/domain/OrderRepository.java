package demo.domain;

import org.jboss.logging.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * Created by yefeiw on 6/25/17.
 */

    public interface OrderRepository extends JpaRepository<Order,String> {
     Order findById(String id, Pageable pageable);
     Order deleteById(String id);

    }
