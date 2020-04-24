package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PaiementDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Paiement}.
 */
public interface PaiementService {

    /**
     * Save a paiement.
     *
     * @param paiementDTO the entity to save.
     * @return the persisted entity.
     */
    PaiementDTO save(PaiementDTO paiementDTO);

    /**
     * Get all the paiements.
     *
     * @return the list of entities.
     */
    List<PaiementDTO> findAll();

    /**
     * Get the "id" paiement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaiementDTO> findOne(Long id);

    /**
     * Delete the "id" paiement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
