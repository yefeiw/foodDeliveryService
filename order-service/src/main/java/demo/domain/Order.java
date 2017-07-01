package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;



/**
 * Created by vagrant on 6/23/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@Document
public class Order {
    @Id
    private String id;

    //Name of the customer
    private String customerName;
    //ID used to find the restaurant, if there are multiple restaurants with the same name
    private String providerId;
    //Special Instructions
    private String instruction;
    //Location information of the restaurant
    private String address;
    //Total value of this order;
    private double total;
    // Status of this order
    int status;
    //Content of this order
    List<OrderItem> content;
    @JsonCreator
    public Order(@JsonProperty("id")String id,
                        @JsonProperty("name") String customerName,
                      @JsonProperty("providerId") String providerId,
                      @JsonProperty("instruction") String instruction,
                      @JsonProperty("address") String address,
                 @JsonProperty("list") List<OrderItem> content) {
        this.id = id;
        this.customerName = customerName;
        this.instruction= instruction;
        this.providerId = providerId;
        this.address = address;
        this.content = content;
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        double ret = 0.0;
        for (OrderItem item : content) {
            ret += item.getPrice();
        }
        return ret;
    }

}
