package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CommandeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CommandeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Commande}.
 */
@RestController
@RequestMapping("/api")
public class CommandeResource {

    private final Logger log = LoggerFactory.getLogger(CommandeResource.class);

    private static final String ENTITY_NAME = "commande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommandeService commandeService;

    public CommandeResource(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    /**
     * {@code POST  /commandes} : Create a new commande.
     *
     * @param commandeDTO the commandeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commandeDTO, or with status {@code 400 (Bad Request)} if the commande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commandes")
    public ResponseEntity<CommandeDTO> createCommande(@Valid @RequestBody CommandeDTO commandeDTO) throws URISyntaxException {
        log.debug("REST request to save Commande : {}", commandeDTO);
        if (commandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new commande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommandeDTO result = commandeService.save(commandeDTO);
        return ResponseEntity.created(new URI("/api/commandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commandes} : Updates an existing commande.
     *
     * @param commandeDTO the commandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandeDTO,
     * or with status {@code 400 (Bad Request)} if the commandeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commandes")
    public ResponseEntity<CommandeDTO> updateCommande(@Valid @RequestBody CommandeDTO commandeDTO) throws URISyntaxException {
        log.debug("REST request to update Commande : {}", commandeDTO);
        if (commandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommandeDTO result = commandeService.save(commandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commandes} : get all the commandes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandes in body.
     */
    @GetMapping("/commandes")
    public List<CommandeDTO> getAllCommandes() {
        log.debug("REST request to get all Commandes");
        return commandeService.findAll();
    }

    /**
     * {@code GET  /commandes/:id} : get the "id" commande.
     *
     * @param id the id of the commandeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commandeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commandes/{id}")
    public ResponseEntity<CommandeDTO> getCommande(@PathVariable Long id) {
        log.debug("REST request to get Commande : {}", id);
        Optional<CommandeDTO> commandeDTO = commandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commandeDTO);
    }

    /**
     * {@code DELETE  /commandes/:id} : delete the "id" commande.
     *
     * @param id the id of the commandeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commandes/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        log.debug("REST request to delete Commande : {}", id);
        commandeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
