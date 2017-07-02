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

import javax.smartcardio.Card;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final static String ORDER_EVENT_URL = "http://localhost:9001/order/event";
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment getPaymentByOrderId(String id) {
        return paymentRepository.findByOrderId(id);
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
    public void deleteAll() {
        paymentRepository.deleteAll();
    }
    @Override
    public Payment createPayment(Invoice invoice) {
        Payment payment = new Payment(invoice.getOrderId());
        paymentRepository.save(payment);
        System.out.println("Payment created with ID "+payment.getId() + " for order "+payment.getOrderId());
        return payment;
    }

    @Override
    public void fulfillPayment(Payment payment, CardInfo info) {
        try {
            //validate card
            if (!validateCard(info)) {
                System.out.println("Card information not valid, will reject now");
                payment.setPaymentStatus(PaymentStatus.DECLINED);
            } else {
                //determine the payment status randomly
                int i = new Random().nextInt(2);
                if (i == 0) {
                    System.out.println("Payment succeeded");
                    payment.setPaymentStatus(PaymentStatus.APPROVED);
                } else {
                    System.out.println("Payment failed");
                    payment.setPaymentStatus(PaymentStatus.DECLINED);
                }
            }
            OrderEvent orderEvent = new OrderEvent(payment);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(ORDER_EVENT_URL, orderEvent, ResponseEntity.class);
            System.out.println("Payment processing finished for id "+payment.getId());
            this.paymentRepository.save(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateCard(CardInfo info) {
        //In reality, it will be put to card service
        //Here we are returning dummy values.
        return info.isValid();
    }



}
