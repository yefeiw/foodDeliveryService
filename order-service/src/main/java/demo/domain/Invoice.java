package demo.domain;

import lombok.Data;

@Data
public class Invoice {
    private String orderId;
    private double price;

    public Invoice(String orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }
}

