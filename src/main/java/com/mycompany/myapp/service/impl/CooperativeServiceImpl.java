package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CooperativeService;
import com.mycompany.myapp.domain.Cooperative;
import com.mycompany.myapp.repository.CooperativeRepository;
import com.mycompany.myapp.service.dto.CooperativeDTO;
import com.mycompany.myapp.service.mapper.CooperativeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Cooperative}.
 */
@Service
@Transactional
public class CooperativeServiceImpl implements CooperativeService {

    private final Logger log = LoggerFactory.getLogger(CooperativeServiceImpl.class);

    private final CooperativeRepository cooperativeRepository;

    private final CooperativeMapper cooperativeMapper;

    public CooperativeServiceImpl(CooperativeRepository cooperativeRepository, CooperativeMapper cooperativeMapper) {
        this.cooperativeRepository = cooperativeRepository;
        this.cooperativeMapper = cooperativeMapper;
    }

    /**
     * Save a cooperative.
     *
     * @param cooperativeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CooperativeDTO save(CooperativeDTO cooperativeDTO) {
        log.debug("Request to save Cooperative : {}", cooperativeDTO);
        Cooperative cooperative = cooperativeMapper.toEntity(cooperativeDTO);
        cooperative = cooperativeRepository.save(cooperative);
        return cooperativeMapper.toDto(cooperative);
    }

    /**
     * Get all the cooperatives.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CooperativeDTO> findAll() {
        log.debug("Request to get all Cooperatives");
        return cooperativeRepository.findAllWithEagerRelationships().stream()
            .map(cooperativeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the cooperatives with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CooperativeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cooperativeRepository.findAllWithEagerRelationships(pageable).map(cooperativeMapper::toDto);
    }

    /**
     * Get one cooperative by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CooperativeDTO> findOne(Long id) {
        log.debug("Request to get Cooperative : {}", id);
        return cooperativeRepository.findOneWithEagerRelationships(id)
            .map(cooperativeMapper::toDto);
    }

    /**
     * Delete the cooperative by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cooperative : {}", id);
        cooperativeRepository.deleteById(id);
    }
}
