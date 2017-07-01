package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by yefeiw on 6/25/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderItem {
    //name of this item
    private String name;
    //id of the restaurant
    private String providerId;
    //special notice of this item
    private String notice;
    //price of this item
    private double price;
    public OrderItem(
                 @JsonProperty("name") String name,
                 @JsonProperty("providerId") String providerId,
                 @JsonProperty("notice") String notice,
                 @JsonProperty("price") double price) {
        this.name = name;
        this.notice = notice;
        this.providerId = providerId;
        this.price = price;
    }
}
