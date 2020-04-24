package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PaiementService;
import com.mycompany.myapp.domain.Paiement;
import com.mycompany.myapp.repository.PaiementRepository;
import com.mycompany.myapp.service.dto.PaiementDTO;
import com.mycompany.myapp.service.mapper.PaiementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Paiement}.
 */
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

    private final Logger log = LoggerFactory.getLogger(PaiementServiceImpl.class);

    private final PaiementRepository paiementRepository;

    private final PaiementMapper paiementMapper;

    public PaiementServiceImpl(PaiementRepository paiementRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementMapper = paiementMapper;
    }

    /**
     * Save a paiement.
     *
     * @param paiementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaiementDTO save(PaiementDTO paiementDTO) {
        log.debug("Request to save Paiement : {}", paiementDTO);
        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        paiement = paiementRepository.save(paiement);
        return paiementMapper.toDto(paiement);
    }

    /**
     * Get all the paiements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> findAll() {
        log.debug("Request to get all Paiements");
        return paiementRepository.findAll().stream()
            .map(paiementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one paiement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaiementDTO> findOne(Long id) {
        log.debug("Request to get Paiement : {}", id);
        return paiementRepository.findById(id)
            .map(paiementMapper::toDto);
    }

    /**
     * Delete the paiement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paiement : {}", id);
        paiementRepository.deleteById(id);
    }
}
