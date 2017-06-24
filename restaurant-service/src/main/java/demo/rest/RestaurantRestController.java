package demo.rest;

import demo.domain.Restaurant;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yefeiw on 6/23/17.
 */
@RestController
public class RestaurantRestController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/provider",method = RequestMethod.GET)
    Page<Restaurant> findAll (@RequestParam(value = ))
}
