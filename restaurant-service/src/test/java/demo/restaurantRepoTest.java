package demo;

import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by yefeiw on 6/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
@WebAppConfiguration
public class restaurantRepoTest {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void saveRestaurant() {
        this.restaurantRepository.save(new Restaurant("Alibaba","1a2b3c4d","Empty Description","123 S Main St"));
        assertThat(this.restaurantRepository.findAllByName("Alibaba", new PageRequest(0,1)).getContent().get(0).getName()).isEqualTo("Alibaba");
    }
    @Test
    public void getRestaurantName() {
        //Test: save two restaurants of same name and different ID. get ID should get one. get Name should get two.
        final String restaurantName = "SameName";
        final String id1 = "11223344";
        final String id2 = "55667788";
        Restaurant firstRestaurant = new Restaurant(restaurantName, id1, "Emtpy","Empty");
        Restaurant secondRestaurant = new Restaurant(restaurantName,id2, "Empty","Empty");
        this.restaurantRepository.save(firstRestaurant);
        this.restaurantRepository.save(secondRestaurant);
        //Assertions
        assertTrue(this.restaurantRepository.findAllByName(restaurantName,new PageRequest(0,10)).getTotalElements() == 2);
        assertTrue(this.restaurantRepository.findByProviderID(id1).getProviderID().equals(id1));
        assertTrue(this.restaurantRepository.findByProviderID(id2).getProviderID().equals(id2));
    }
}
