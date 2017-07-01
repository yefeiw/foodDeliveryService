package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Invoice {
    private String orderId;
    private double price;

    public Invoice(String orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }
}

