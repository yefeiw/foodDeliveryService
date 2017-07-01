package demo.rest;

import demo.domain.Restaurant;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by yefeiw on 6/23/17.
 */
@RestController
public class RestaurantRestController {
    private final int defaultPageSize = 10;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String isOk() {
        return "OK";
    }

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    Page<Restaurant> findAll(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "page") int page,
                             @RequestParam(value = "size", required = false) Integer size) {
        int pageSize = (size == null) ? defaultPageSize : size;
        if (name != null) {
            return restaurantService.findByName(name, new PageRequest(page, pageSize));
        } else {
            return restaurantService.findAll(new PageRequest(page, pageSize));
        }
    }

    @RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
    Restaurant findById(@PathVariable(value = "id") String id) {
        return restaurantService.findById(id);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.POST)
    List<Restaurant> save(@RequestBody List<Restaurant> restaurants) {
        return restaurantService.saveRestaurants(restaurants);
    }

    @RequestMapping(value = "/provider", method = RequestMethod.DELETE)
    void deleteAll() {
        restaurantService.deleteAll();
    }

    @RequestMapping(value = "/provider/{id}", method = RequestMethod.DELETE)
    void deleteById(@PathVariable(value = "id") String id) {
        restaurantService.deleteById(id);
    }
}
