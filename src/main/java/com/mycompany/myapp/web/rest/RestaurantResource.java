package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.RestaurantService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.RestaurantDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Restaurant}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantResource.class);

    private static final String ENTITY_NAME = "restaurant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantService restaurantService;

    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * {@code POST  /restaurants} : Create a new restaurant.
     *
     * @param restaurantDTO the restaurantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantDTO, or with status {@code 400 (Bad Request)} if the restaurant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        log.debug("REST request to save Restaurant : {}", restaurantDTO);
        if (restaurantDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantDTO result = restaurantService.save(restaurantDTO);
        return ResponseEntity.created(new URI("/api/restaurants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurants} : Updates an existing restaurant.
     *
     * @param restaurantDTO the restaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurants")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        log.debug("REST request to update Restaurant : {}", restaurantDTO);
        if (restaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RestaurantDTO result = restaurantService.save(restaurantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, restaurantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /restaurants} : get all the restaurants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurants in body.
     */
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants(Pageable pageable) {
        log.debug("REST request to get a page of Restaurants");
        Page<RestaurantDTO> page = restaurantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurants/:id} : get the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        log.debug("REST request to get Restaurant : {}", id);
        Optional<RestaurantDTO> restaurantDTO = restaurantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantDTO);
    }

    /**
     * {@code DELETE  /restaurants/:id} : delete the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        log.debug("REST request to delete Restaurant : {}", id);
        restaurantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
