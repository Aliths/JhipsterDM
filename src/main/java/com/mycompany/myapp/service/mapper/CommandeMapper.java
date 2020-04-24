package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commande} and its DTO {@link CommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, RestaurantMapper.class, PanierMapper.class, PaiementMapper.class})
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    @Mapping(source = "panier.id", target = "panierId")
    @Mapping(source = "paiement.id", target = "paiementId")
    CommandeDTO toDto(Commande commande);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "restaurantId", target = "restaurant")
    @Mapping(source = "panierId", target = "panier")
    @Mapping(source = "paiementId", target = "paiement")
    Commande toEntity(CommandeDTO commandeDTO);

    default Commande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}
