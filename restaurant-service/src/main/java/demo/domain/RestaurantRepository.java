package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by yefeiw on 6/23/17.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    Restaurant findByProviderID(@Param("providerId") String id);
    Page<Restaurant> findAllByName(@Param("name") String name, Pageable pageable);
    Page<Restaurant> findAll(Pageable pageable);
}
