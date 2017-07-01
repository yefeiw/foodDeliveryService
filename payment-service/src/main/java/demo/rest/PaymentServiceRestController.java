package demo.rest;

import demo.domain.Payment;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentServiceRestController {
    private PaymentService paymentService;

    @Autowired
    public PaymentServiceRestController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        String paymentId = this.paymentService.createPayment(payment);
        if (paymentId != null) {
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>((Payment) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {
        return new ResponseEntity<>(this.paymentService.getPaymentById(paymentId), HttpStatus.OK);
    }

}
