package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String>{
    Payment findById(String id);
    Payment findByOrderId(String orderId);
}
