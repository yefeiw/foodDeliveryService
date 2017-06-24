package demo;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by vagrant on 6/23/17.
 */
@Entity
@Data

//Assumptions: 1. Only One Language supported. 2. The currency unit is uniform.
public class MenuItem {
    private int id;
    //Name of the item
    private String name;
    //introduction of the item
    private String description;
    //price of the item;
    double price;

}
