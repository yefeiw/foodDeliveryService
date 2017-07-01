package demo.rest;

import demo.domain.Invoice;
import demo.domain.Payment;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentServiceRestController {
    private PaymentService paymentService;
    private final String defaultPageNum = "0";
    private final String defaultPageSize = "2";
    @Autowired
    public PaymentServiceRestController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity<Invoice> createPayment(@RequestBody Invoice invoice) {
        String paymentId = this.paymentService.createPayment(invoice);
        if (paymentId != null) {
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>((Invoice) null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    Page<Payment> findAll(
            @RequestParam(name = "page", required = false, defaultValue = defaultPageNum) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) Integer size) {
        return this.paymentService.findAll(new PageRequest(page,size));
    }
    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {
        return new ResponseEntity<>(this.paymentService.getPaymentById(paymentId), HttpStatus.OK);
    }

}
