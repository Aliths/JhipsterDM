package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PanierService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PanierDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Panier}.
 */
@RestController
@RequestMapping("/api")
public class PanierResource {

    private final Logger log = LoggerFactory.getLogger(PanierResource.class);

    private static final String ENTITY_NAME = "panier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PanierService panierService;

    public PanierResource(PanierService panierService) {
        this.panierService = panierService;
    }

    /**
     * {@code POST  /paniers} : Create a new panier.
     *
     * @param panierDTO the panierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new panierDTO, or with status {@code 400 (Bad Request)} if the panier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paniers")
    public ResponseEntity<PanierDTO> createPanier(@Valid @RequestBody PanierDTO panierDTO) throws URISyntaxException {
        log.debug("REST request to save Panier : {}", panierDTO);
        if (panierDTO.getId() != null) {
            throw new BadRequestAlertException("A new panier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PanierDTO result = panierService.save(panierDTO);
        return ResponseEntity.created(new URI("/api/paniers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paniers} : Updates an existing panier.
     *
     * @param panierDTO the panierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated panierDTO,
     * or with status {@code 400 (Bad Request)} if the panierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the panierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paniers")
    public ResponseEntity<PanierDTO> updatePanier(@Valid @RequestBody PanierDTO panierDTO) throws URISyntaxException {
        log.debug("REST request to update Panier : {}", panierDTO);
        if (panierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PanierDTO result = panierService.save(panierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, panierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paniers} : get all the paniers.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paniers in body.
     */
    @GetMapping("/paniers")
    public ResponseEntity<List<PanierDTO>> getAllPaniers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Paniers");
        Page<PanierDTO> page;
        if (eagerload) {
            page = panierService.findAllWithEagerRelationships(pageable);
        } else {
            page = panierService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paniers/:id} : get the "id" panier.
     *
     * @param id the id of the panierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the panierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paniers/{id}")
    public ResponseEntity<PanierDTO> getPanier(@PathVariable Long id) {
        log.debug("REST request to get Panier : {}", id);
        Optional<PanierDTO> panierDTO = panierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(panierDTO);
    }

    /**
     * {@code DELETE  /paniers/:id} : delete the "id" panier.
     *
     * @param id the id of the panierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paniers/{id}")
    public ResponseEntity<Void> deletePanier(@PathVariable Long id) {
        log.debug("REST request to delete Panier : {}", id);
        panierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
