package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class OrderEvent {
    public enum OrderEventType {
        //This order has been created and no responses has been received.
        PROCESSING,
        //This order has been successfully created.
        SUCCEED,
        //This order has failed to be processed.
        FAILURE;
    }

    @Id
    private String id;
    private OrderEventType type;
    private String orderId;

    public OrderEvent(Payment payment) {
        this.orderId = payment.getOrderId();
        if (payment.getPaymentStatus().equals(PaymentStatus.APPROVED)) {
            this.type = OrderEventType.SUCCEED;
        } else if (payment.getPaymentStatus().equals(PaymentStatus.DECLINED)) {
            this.type = OrderEventType.FAILURE;
        } else {
            throw new IllegalArgumentException("Illegal payment status " + payment.getPaymentStatus());
        }
    }
}
