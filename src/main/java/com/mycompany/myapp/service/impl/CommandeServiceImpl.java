package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CommandeService;
import com.mycompany.myapp.domain.Commande;
import com.mycompany.myapp.repository.CommandeRepository;
import com.mycompany.myapp.service.dto.CommandeDTO;
import com.mycompany.myapp.service.mapper.CommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Commande}.
 */
@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private final Logger log = LoggerFactory.getLogger(CommandeServiceImpl.class);

    private final CommandeRepository commandeRepository;

    private final CommandeMapper commandeMapper;

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    /**
     * Save a commande.
     *
     * @param commandeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommandeDTO save(CommandeDTO commandeDTO) {
        log.debug("Request to save Commande : {}", commandeDTO);
        Commande commande = commandeMapper.toEntity(commandeDTO);
        commande = commandeRepository.save(commande);
        return commandeMapper.toDto(commande);
    }

    /**
     * Get all the commandes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CommandeDTO> findAll() {
        log.debug("Request to get all Commandes");
        return commandeRepository.findAll().stream()
            .map(commandeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one commande by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommandeDTO> findOne(Long id) {
        log.debug("Request to get Commande : {}", id);
        return commandeRepository.findById(id)
            .map(commandeMapper::toDto);
    }

    /**
     * Delete the commande by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commande : {}", id);
        commandeRepository.deleteById(id);
    }
}
