package demo.rest;

import demo.domain.CardInfo;
import demo.domain.Invoice;
import demo.domain.Payment;
import demo.domain.PaymentStatus;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;

@RestController
public class PaymentServiceRestController {
    private PaymentService paymentService;
    private final String defaultPageNum = "0";
    private final String defaultPageSize = "2";
    @Autowired
    public PaymentServiceRestController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payment",method = RequestMethod.DELETE)
    public void deleteAll() {
        this.paymentService.deleteAll();
    }
    //Notice: POST method is creating a payment, it does not mean that the payment will be fulfilled.
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity createPayment(@RequestBody Invoice invoice) {
        System.out.println("Payment service received invoice from "+invoice);
        if(this.paymentService.getPaymentByOrderId(invoice.getOrderId())!= null) {
            System.out.println("Multiple payments for the same order not allowed");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Payment payment = this.paymentService.createPayment(invoice);
        return new ResponseEntity(HttpStatus.OK);
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



    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.PUT)
    public HttpStatus Payment(@PathVariable String paymentId, @RequestBody CardInfo info) {
       Payment payment =  paymentService.getPaymentById(paymentId);
       paymentService.fulfillPayment(payment,info);
       return HttpStatus.OK;
    }

}
