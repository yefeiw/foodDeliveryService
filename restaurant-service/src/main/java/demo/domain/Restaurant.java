package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;


/**
 * Created by vagrant on 6/23/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@Document
public class Restaurant {
    @Id
    @JsonIgnore
    private String Id;

    //Name of the restaurant
    private String name;
    //ID used to find the restaurant, if there are multiple restaurants with the same name
    private String providerID;
    //Introduction of a demo.domain.Restaurant
    private String description;
    //Location information of the restaurant
    private String address;
    //Menu of this restaurant
    private List<demo.domain.MenuItem> menu;

    @JsonCreator
    public Restaurant(@JsonProperty("name") String name,
                      @JsonProperty("id") String providerID,
                      @JsonProperty("description") String description,
                      @JsonProperty("address") String address,
                      @JsonProperty("menu") List<MenuItem> menu) {
        this.name = name;
        this.description = description;
        this.providerID = providerID;
        this.address = address;
        this.menu = menu;
    }

    public List<MenuItem> getMenu() {
        return this.menu;
    }
}
