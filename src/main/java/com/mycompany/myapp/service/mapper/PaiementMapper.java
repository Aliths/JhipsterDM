package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paiement} and its DTO {@link PaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaiementMapper extends EntityMapper<PaiementDTO, Paiement> {



    default Paiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setId(id);
        return paiement;
    }
}
