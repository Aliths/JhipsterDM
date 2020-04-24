package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CooperativeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Cooperative}.
 */
public interface CooperativeService {

    /**
     * Save a cooperative.
     *
     * @param cooperativeDTO the entity to save.
     * @return the persisted entity.
     */
    CooperativeDTO save(CooperativeDTO cooperativeDTO);

    /**
     * Get all the cooperatives.
     *
     * @return the list of entities.
     */
    List<CooperativeDTO> findAll();

    /**
     * Get all the cooperatives with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CooperativeDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" cooperative.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CooperativeDTO> findOne(Long id);

    /**
     * Delete the "id" cooperative.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
