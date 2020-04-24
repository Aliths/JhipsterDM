package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MyblogApp;
import com.mycompany.myapp.domain.Cooperative;
import com.mycompany.myapp.repository.CooperativeRepository;
import com.mycompany.myapp.service.CooperativeService;
import com.mycompany.myapp.service.dto.CooperativeDTO;
import com.mycompany.myapp.service.mapper.CooperativeMapper;
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

/**
 * Integration tests for the {@link CooperativeResource} REST controller.
 */
@SpringBootTest(classes = MyblogApp.class)
public class CooperativeResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private CooperativeRepository cooperativeRepository;

    @Mock
    private CooperativeRepository cooperativeRepositoryMock;

    @Autowired
    private CooperativeMapper cooperativeMapper;

    @Mock
    private CooperativeService cooperativeServiceMock;

    @Autowired
    private CooperativeService cooperativeService;

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

    private MockMvc restCooperativeMockMvc;

    private Cooperative cooperative;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CooperativeResource cooperativeResource = new CooperativeResource(cooperativeService);
        this.restCooperativeMockMvc = MockMvcBuilders.standaloneSetup(cooperativeResource)
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
    public static Cooperative createEntity(EntityManager em) {
        Cooperative cooperative = new Cooperative()
            .nom(DEFAULT_NOM);
        return cooperative;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cooperative createUpdatedEntity(EntityManager em) {
        Cooperative cooperative = new Cooperative()
            .nom(UPDATED_NOM);
        return cooperative;
    }

    @BeforeEach
    public void initTest() {
        cooperative = createEntity(em);
    }

    @Test
    @Transactional
    public void createCooperative() throws Exception {
        int databaseSizeBeforeCreate = cooperativeRepository.findAll().size();

        // Create the Cooperative
        CooperativeDTO cooperativeDTO = cooperativeMapper.toDto(cooperative);
        restCooperativeMockMvc.perform(post("/api/cooperatives")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cooperativeDTO)))
            .andExpect(status().isCreated());

        // Validate the Cooperative in the database
        List<Cooperative> cooperativeList = cooperativeRepository.findAll();
        assertThat(cooperativeList).hasSize(databaseSizeBeforeCreate + 1);
        Cooperative testCooperative = cooperativeList.get(cooperativeList.size() - 1);
        assertThat(testCooperative.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    public void createCooperativeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cooperativeRepository.findAll().size();

        // Create the Cooperative with an existing ID
        cooperative.setId(1L);
        CooperativeDTO cooperativeDTO = cooperativeMapper.toDto(cooperative);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCooperativeMockMvc.perform(post("/api/cooperatives")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cooperativeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cooperative in the database
        List<Cooperative> cooperativeList = cooperativeRepository.findAll();
        assertThat(cooperativeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCooperatives() throws Exception {
        // Initialize the database
        cooperativeRepository.saveAndFlush(cooperative);

        // Get all the cooperativeList
        restCooperativeMockMvc.perform(get("/api/cooperatives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cooperative.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCooperativesWithEagerRelationshipsIsEnabled() throws Exception {
        CooperativeResource cooperativeResource = new CooperativeResource(cooperativeServiceMock);
        when(cooperativeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCooperativeMockMvc = MockMvcBuilders.standaloneSetup(cooperativeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCooperativeMockMvc.perform(get("/api/cooperatives?eagerload=true"))
        .andExpect(status().isOk());

        verify(cooperativeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCooperativesWithEagerRelationshipsIsNotEnabled() throws Exception {
        CooperativeResource cooperativeResource = new CooperativeResource(cooperativeServiceMock);
            when(cooperativeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCooperativeMockMvc = MockMvcBuilders.standaloneSetup(cooperativeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCooperativeMockMvc.perform(get("/api/cooperatives?eagerload=true"))
        .andExpect(status().isOk());

            verify(cooperativeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCooperative() throws Exception {
        // Initialize the database
        cooperativeRepository.saveAndFlush(cooperative);

        // Get the cooperative
        restCooperativeMockMvc.perform(get("/api/cooperatives/{id}", cooperative.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cooperative.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM));
    }

    @Test
    @Transactional
    public void getNonExistingCooperative() throws Exception {
        // Get the cooperative
        restCooperativeMockMvc.perform(get("/api/cooperatives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCooperative() throws Exception {
        // Initialize the database
        cooperativeRepository.saveAndFlush(cooperative);

        int databaseSizeBeforeUpdate = cooperativeRepository.findAll().size();

        // Update the cooperative
        Cooperative updatedCooperative = cooperativeRepository.findById(cooperative.getId()).get();
        // Disconnect from session so that the updates on updatedCooperative are not directly saved in db
        em.detach(updatedCooperative);
        updatedCooperative
            .nom(UPDATED_NOM);
        CooperativeDTO cooperativeDTO = cooperativeMapper.toDto(updatedCooperative);

        restCooperativeMockMvc.perform(put("/api/cooperatives")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cooperativeDTO)))
            .andExpect(status().isOk());

        // Validate the Cooperative in the database
        List<Cooperative> cooperativeList = cooperativeRepository.findAll();
        assertThat(cooperativeList).hasSize(databaseSizeBeforeUpdate);
        Cooperative testCooperative = cooperativeList.get(cooperativeList.size() - 1);
        assertThat(testCooperative.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    public void updateNonExistingCooperative() throws Exception {
        int databaseSizeBeforeUpdate = cooperativeRepository.findAll().size();

        // Create the Cooperative
        CooperativeDTO cooperativeDTO = cooperativeMapper.toDto(cooperative);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCooperativeMockMvc.perform(put("/api/cooperatives")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cooperativeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cooperative in the database
        List<Cooperative> cooperativeList = cooperativeRepository.findAll();
        assertThat(cooperativeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCooperative() throws Exception {
        // Initialize the database
        cooperativeRepository.saveAndFlush(cooperative);

        int databaseSizeBeforeDelete = cooperativeRepository.findAll().size();

        // Delete the cooperative
        restCooperativeMockMvc.perform(delete("/api/cooperatives/{id}", cooperative.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cooperative> cooperativeList = cooperativeRepository.findAll();
        assertThat(cooperativeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
