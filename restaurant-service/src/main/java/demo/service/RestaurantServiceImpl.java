package demo.service;

import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yefeiw on 6/23/17.
 */
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Override
    public Page<Restaurant> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Restaurant> findAll(Pageable pageable) {
    public Restaurant findById(String id) {
        return repository.findByProviderID(id);
    }

        @Override
        public Page<Restaurant> findAll(Pageable pageable) {
    public Page<Restaurant> findByName(String name, Pageable pageable) {
        return repository.findAllByName(name,pageable);
    }

            @Override
            public Page<Restaurant> findAll(Pageable pageable) {
    public List<Restaurant> saveRestaurants(List<Restaurant> restaurantList) {
        return repository.save(restaurantList.stream().collect(Collectors.toList()));
    }

                @Override
                public Page<Restaurant> findAll(Pageable pageable) {
    public void deleteById(String id) {
        repository.delete(id);
                    }
    }

                    @Override
                    public Page<Restaurant> findAll(Pageable pageable) {
    public void deleteAll() {
        repository.deleteAll();
    }

    public int update(Restaurant restaurant) {
        if (repository.findOne(restaurant.getProviderID()) == null) {
            //not found;
            return 0;
        } else {
            repository.save(restaurant);
            return 1;
        }

    }
}
