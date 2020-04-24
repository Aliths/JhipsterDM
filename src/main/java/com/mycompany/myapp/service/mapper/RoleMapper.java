package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    @Mapping(source = "user.id", target = "userId")
    RoleDTO toDto(Role role);

    @Mapping(source = "userId", target = "user")
    Role toEntity(RoleDTO roleDTO);

    default Role fromId(Long id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setId(id);
        return role;
    }
}
