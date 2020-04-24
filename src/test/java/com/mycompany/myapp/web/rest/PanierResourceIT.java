package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MyblogApp;
import com.mycompany.myapp.domain.Panier;
import com.mycompany.myapp.repository.PanierRepository;
import com.mycompany.myapp.service.PanierService;
import com.mycompany.myapp.service.dto.PanierDTO;
import com.mycompany.myapp.service.mapper.PanierMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Etat;
/**
 * Integration tests for the {@link PanierResource} REST controller.
 */
@SpringBootTest(classes = MyblogApp.class)
public class PanierResourceIT {

    private static final Etat DEFAULT_ETAT = Etat.VALIDE;
    private static final Etat UPDATED_ETAT = Etat.ENCOURS;

    @Autowired
    private PanierRepository panierRepository;

    @Mock
    private PanierRepository panierRepositoryMock;

    @Autowired
    private PanierMapper panierMapper;

    @Mock
    private PanierService panierServiceMock;

    @Autowired
    private PanierService panierService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPanierMockMvc;

    private Panier panier;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PanierResource panierResource = new PanierResource(panierService);
        this.restPanierMockMvc = MockMvcBuilders.standaloneSetup(panierResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Panier createEntity(EntityManager em) {
        Panier panier = new Panier()
            .etat(DEFAULT_ETAT);
        return panier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Panier createUpdatedEntity(EntityManager em) {
        Panier panier = new Panier()
            .etat(UPDATED_ETAT);
        return panier;
    }

    @BeforeEach
    public void initTest() {
        panier = createEntity(em);
    }

    @Test
    @Transactional
    public void createPanier() throws Exception {
        int databaseSizeBeforeCreate = panierRepository.findAll().size();

        // Create the Panier
        PanierDTO panierDTO = panierMapper.toDto(panier);
        restPanierMockMvc.perform(post("/api/paniers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isCreated());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeCreate + 1);
        Panier testPanier = panierList.get(panierList.size() - 1);
        assertThat(testPanier.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    public void createPanierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = panierRepository.findAll().size();

        // Create the Panier with an existing ID
        panier.setId(1L);
        PanierDTO panierDTO = panierMapper.toDto(panier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPanierMockMvc.perform(post("/api/paniers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEtatIsRequired() throws Exception {
        int databaseSizeBeforeTest = panierRepository.findAll().size();
        // set the field null
        panier.setEtat(null);

        // Create the Panier, which fails.
        PanierDTO panierDTO = panierMapper.toDto(panier);

        restPanierMockMvc.perform(post("/api/paniers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isBadRequest());

        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaniers() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        // Get all the panierList
        restPanierMockMvc.perform(get("/api/paniers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(panier.getId().intValue())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPaniersWithEagerRelationshipsIsEnabled() throws Exception {
        PanierResource panierResource = new PanierResource(panierServiceMock);
        when(panierServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restPanierMockMvc = MockMvcBuilders.standaloneSetup(panierResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPanierMockMvc.perform(get("/api/paniers?eagerload=true"))
        .andExpect(status().isOk());

        verify(panierServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPaniersWithEagerRelationshipsIsNotEnabled() throws Exception {
        PanierResource panierResource = new PanierResource(panierServiceMock);
            when(panierServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restPanierMockMvc = MockMvcBuilders.standaloneSetup(panierResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPanierMockMvc.perform(get("/api/paniers?eagerload=true"))
        .andExpect(status().isOk());

            verify(panierServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        // Get the panier
        restPanierMockMvc.perform(get("/api/paniers/{id}", panier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(panier.getId().intValue()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPanier() throws Exception {
        // Get the panier
        restPanierMockMvc.perform(get("/api/paniers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        int databaseSizeBeforeUpdate = panierRepository.findAll().size();

        // Update the panier
        Panier updatedPanier = panierRepository.findById(panier.getId()).get();
        // Disconnect from session so that the updates on updatedPanier are not directly saved in db
        em.detach(updatedPanier);
        updatedPanier
            .etat(UPDATED_ETAT);
        PanierDTO panierDTO = panierMapper.toDto(updatedPanier);

        restPanierMockMvc.perform(put("/api/paniers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isOk());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeUpdate);
        Panier testPanier = panierList.get(panierList.size() - 1);
        assertThat(testPanier.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingPanier() throws Exception {
        int databaseSizeBeforeUpdate = panierRepository.findAll().size();

        // Create the Panier
        PanierDTO panierDTO = panierMapper.toDto(panier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPanierMockMvc.perform(put("/api/paniers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(panierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Panier in the database
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePanier() throws Exception {
        // Initialize the database
        panierRepository.saveAndFlush(panier);

        int databaseSizeBeforeDelete = panierRepository.findAll().size();

        // Delete the panier
        restPanierMockMvc.perform(delete("/api/paniers/{id}", panier.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Panier> panierList = panierRepository.findAll();
        assertThat(panierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
