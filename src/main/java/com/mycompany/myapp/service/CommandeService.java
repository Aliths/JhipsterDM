package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CommandeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Commande}.
 */
public interface CommandeService {

    /**
     * Save a commande.
     *
     * @param commandeDTO the entity to save.
     * @return the persisted entity.
     */
    CommandeDTO save(CommandeDTO commandeDTO);

    /**
     * Get all the commandes.
     *
     * @return the list of entities.
     */
    List<CommandeDTO> findAll();

    /**
     * Get the "id" commande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommandeDTO> findOne(Long id);

    /**
     * Delete the "id" commande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
