package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by vagrant on 6/23/17.
 */
@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//Assumptions: 1. Only One Language supported. 2. The currency unit is uniform.
public class MenuItem {

    //ID value in string
    private String id;
    //Name of the item
    private String name;
    //introduction of the item
    private String description;
    //price of the item;
    private double price;
}
