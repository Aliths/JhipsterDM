package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RestaurantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {

    @Mapping(source = "user.id", target = "userId")
    RestaurantDTO toDto(Restaurant restaurant);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "cooperatives", ignore = true)
    @Mapping(target = "removeCooperative", ignore = true)
    Restaurant toEntity(RestaurantDTO restaurantDTO);

    default Restaurant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        return restaurant;
    }
}
