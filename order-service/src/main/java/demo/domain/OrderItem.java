package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by yefeiw on 6/25/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderItem {
    //name of this item
    private String name;
    //id of the restaurant
    private String providerID;
    //special notice of this item
    private String notice;
    //price of this item
    private double price;
}
