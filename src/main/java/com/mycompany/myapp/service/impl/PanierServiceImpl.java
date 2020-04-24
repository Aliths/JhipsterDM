package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PanierService;
import com.mycompany.myapp.domain.Panier;
import com.mycompany.myapp.repository.PanierRepository;
import com.mycompany.myapp.service.dto.PanierDTO;
import com.mycompany.myapp.service.mapper.PanierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Panier}.
 */
@Service
@Transactional
public class PanierServiceImpl implements PanierService {

    private final Logger log = LoggerFactory.getLogger(PanierServiceImpl.class);

    private final PanierRepository panierRepository;

    private final PanierMapper panierMapper;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    /**
     * Save a panier.
     *
     * @param panierDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PanierDTO save(PanierDTO panierDTO) {
        log.debug("Request to save Panier : {}", panierDTO);
        Panier panier = panierMapper.toEntity(panierDTO);
        panier = panierRepository.save(panier);
        return panierMapper.toDto(panier);
    }

    /**
     * Get all the paniers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PanierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paniers");
        return panierRepository.findAll(pageable)
            .map(panierMapper::toDto);
    }

    /**
     * Get all the paniers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PanierDTO> findAllWithEagerRelationships(Pageable pageable) {
        return panierRepository.findAllWithEagerRelationships(pageable).map(panierMapper::toDto);
    }

    /**
     * Get one panier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PanierDTO> findOne(Long id) {
        log.debug("Request to get Panier : {}", id);
        return panierRepository.findOneWithEagerRelationships(id)
            .map(panierMapper::toDto);
    }

    /**
     * Delete the panier by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.deleteById(id);
    }
}
