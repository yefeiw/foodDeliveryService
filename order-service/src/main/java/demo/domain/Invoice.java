package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Invoice {
    private String orderId;
    private double price;

    @JsonCreator
    public Invoice(@JsonProperty("orderId")String orderId,
                   @JsonProperty("price") double price) {
        this.orderId = orderId;
        this.price = price;
    }
}

