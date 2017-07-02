package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by vagrant on 6/23/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//Assumptions: 1. Only One Language supported. 2. The currency unit is uniform.
public class MenuItem {
    //Name of the item
    private String name;
    //introduction of the item
    private String description;
    //price of the item;
    private double price;

    @JsonCreator
    public MenuItem(@JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("price") double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
