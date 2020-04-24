package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Restaurant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Restaurant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select restaurant from Restaurant restaurant where restaurant.user.login = ?#{principal.username}")
    List<Restaurant> findByUserIsCurrentUser();

}
