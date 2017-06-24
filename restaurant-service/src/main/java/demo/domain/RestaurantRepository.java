package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by yefeiw on 6/23/17.
 */
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant,String> {
    @RestResource(path = "restaurant",rel = "by-name")
    Restaurant findByProviderID(@Param("providerId") String id);
    Page<Restaurant> findAllByName(@Param("name") String name, Pageable pageable);
}
