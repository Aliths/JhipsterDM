package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PanierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Panier} and its DTO {@link PanierDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface PanierMapper extends EntityMapper<PanierDTO, Panier> {


    @Mapping(target = "removeProduit", ignore = true)

    default Panier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Panier panier = new Panier();
        panier.setId(id);
        return panier;
    }
}
