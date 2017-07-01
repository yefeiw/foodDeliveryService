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
    private enum OrderEventType {
        PAY, APPROVE, DECLINE
    }

    @Id
    private String id;
    private OrderEventType type;
    private String orderId;

    public OrderEvent(Payment payment) {
        this.orderId = payment.getOrderId();
        if (payment.getPaymentStatus().equals(PaymentStatus.APPROVED)) {
            this.type = OrderEventType.APPROVE;
        } else if (payment.getPaymentStatus().equals(PaymentStatus.DECLINED)) {
            this.type = OrderEventType.DECLINE;
        } else {
            throw new IllegalArgumentException("Illegal payment status " + payment.getPaymentStatus());
        }
    }
}
