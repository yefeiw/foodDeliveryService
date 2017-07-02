package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Payment {
    @Id
    private String id;

    private String orderId;
    private PaymentStatus paymentStatus;
    private Date timestamp;

    private CardInfo info;
    public Payment(String orderId) {
        this.orderId = orderId;
        this.paymentStatus = PaymentStatus.PROCESSING;
        this.timestamp = new Date();
    }
}

