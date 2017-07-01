package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Payment {
    @Id
    private String id;

    private String orderId;
    private PaymentStatus paymentStatus;

    public Payment(String orderId) {
        this.orderId = orderId;
    }
    @Override
    public String toString() {
        return "Payment {" +
                "paymentId='" + id + '\'' +
                "orderId='" + orderId + '\'' +
                "paymentStatus='" + paymentStatus.toString() + '\'';
    }
}

