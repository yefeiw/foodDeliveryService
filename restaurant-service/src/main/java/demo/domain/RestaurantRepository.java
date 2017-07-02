package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by yefeiw on 6/23/17.
 */
public interface RestaurantRepository extends MongoRepository<demo.domain.Restaurant, String> {
    demo.domain.Restaurant findByProviderID(@Param("providerId") String id);

    Page<demo.domain.Restaurant> findAllByName(@Param("name") String name, Pageable pageable);

    Page<demo.domain.Restaurant> findAll(Pageable pageable);

}
