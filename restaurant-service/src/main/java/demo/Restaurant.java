package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by vagrant on 6/23/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name="PROVIDER")
public class Restaurant {
    @Id
    @GeneratedValue
    private int Id;

    @OneToMany(mappedBy = "restaurant")
    private Menu menu;
    @Embedded
    //Name of the restaurant
    private String name;
    //ID used to find the restaurant, if there are multiple restaurants with the same name
    private String providerID;
    //Introduction of a Restaurant
    private String description;
    //Location information of the restaurant
    private double latitude;
    private double longitude;
    @JsonCreator
    public Restaurant(@JsonProperty("name") String name,
                      @JsonProperty("id") String providerID,
                      @JsonProperty("description") String description,
                      @JsonProperty("latitude") double latitude,
                      @JsonProperty("longitude")double longitude) {
        this.name = name;
        this.description = description;
        this.providerID = providerID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Menu getMenu() {
        return this.menu;
    }
}
