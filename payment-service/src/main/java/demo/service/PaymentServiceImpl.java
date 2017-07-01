package demo.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final static String ORDER_EVENT_URL = "https://localhost:8080/order-service/order/events";
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id);
    }
    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }
    @Override
    public String createPayment(Invoice invoice) {
        String paymentId = null;
        Payment payment = new Payment(invoice.getOrderId());
        try {
            //first, determine the payment status randomly
            int i = new Random().nextInt(2);
            if (i == 0) {
                System.out.println("Payment succeeded");
                payment.setPaymentStatus(PaymentStatus.APPROVED);
            } else {
                System.out.println("Payment failed");
                payment.setPaymentStatus(PaymentStatus.DECLINED);
            }
            //second, send the payment to order events
            OrderEvent orderEvent = new OrderEvent(payment);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity response = restTemplate.postForObject(ORDER_EVENT_URL, orderEvent, ResponseEntity.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                this.paymentRepository.save(payment);
            } else {
                System.out.println("Payments with id "+payment.getId() +" failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return paymentId;
        }
        paymentId = payment.getId();
        return paymentId;
    }


}
