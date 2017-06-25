package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
/*
 * Created by yefeiw on 6/25/17.
 */

    public interface PaymentRepository extends JpaRepository<Payment,String> {
     Payment findById(@Param("id")String id);
     Payment deleteById(@Param("id")String id);

    }
