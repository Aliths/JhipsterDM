package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {RestaurantMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    ProduitDTO toDto(Produit produit);

    @Mapping(source = "restaurantId", target = "restaurant")
    @Mapping(target = "paniers", ignore = true)
    @Mapping(target = "removePanier", ignore = true)
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
