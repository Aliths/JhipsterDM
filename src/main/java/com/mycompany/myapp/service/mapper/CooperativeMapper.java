package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CooperativeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperative} and its DTO {@link CooperativeDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, RestaurantMapper.class})
public interface CooperativeMapper extends EntityMapper<CooperativeDTO, Cooperative> {


    @Mapping(target = "removeUser", ignore = true)
    @Mapping(target = "removeRestaurant", ignore = true)

    default Cooperative fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cooperative cooperative = new Cooperative();
        cooperative.setId(id);
        return cooperative;
    }
}
