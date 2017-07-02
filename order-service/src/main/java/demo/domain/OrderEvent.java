package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * Created by yefeiw on 6/30/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class OrderEvent {
    @Id
    private String id;
    private OrderEventType type;
    private String orderId;
}
