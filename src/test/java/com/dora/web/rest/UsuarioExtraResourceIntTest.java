package com.dora.web.rest;

import com.dora.DoraApp;

import com.dora.domain.UsuarioExtra;
import com.dora.domain.User;
import com.dora.repository.UsuarioExtraRepository;
import com.dora.service.UsuarioExtraService;
import com.dora.repository.search.UsuarioExtraSearchRepository;
import com.dora.service.dto.UsuarioExtraDTO;
import com.dora.service.mapper.UsuarioExtraMapper;
import com.dora.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dora.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UsuarioExtraResource REST controller.
 *
 * @see UsuarioExtraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoraApp.class)
public class UsuarioExtraResourceIntTest {

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    @Autowired
    private UsuarioExtraRepository usuarioExtraRepository;

    @Autowired
    private UsuarioExtraMapper usuarioExtraMapper;

    @Autowired
    private UsuarioExtraService usuarioExtraService;

    @Autowired
    private UsuarioExtraSearchRepository usuarioExtraSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUsuarioExtraMockMvc;

    private UsuarioExtra usuarioExtra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsuarioExtraResource usuarioExtraResource = new UsuarioExtraResource(usuarioExtraService);
        this.restUsuarioExtraMockMvc = MockMvcBuilders.standaloneSetup(usuarioExtraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuarioExtra createEntity(EntityManager em) {
        UsuarioExtra usuarioExtra = new UsuarioExtra()
            .telefone(DEFAULT_TELEFONE);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        usuarioExtra.setUser(user);
        return usuarioExtra;
    }

    @Before
    public void initTest() {
        usuarioExtraSearchRepository.deleteAll();
        usuarioExtra = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarioExtra() throws Exception {
        int databaseSizeBeforeCreate = usuarioExtraRepository.findAll().size();

        // Create the UsuarioExtra
        UsuarioExtraDTO usuarioExtraDTO = usuarioExtraMapper.toDto(usuarioExtra);
        restUsuarioExtraMockMvc.perform(post("/api/usuario-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioExtraDTO)))
            .andExpect(status().isCreated());

        // Validate the UsuarioExtra in the database
        List<UsuarioExtra> usuarioExtraList = usuarioExtraRepository.findAll();
        assertThat(usuarioExtraList).hasSize(databaseSizeBeforeCreate + 1);
        UsuarioExtra testUsuarioExtra = usuarioExtraList.get(usuarioExtraList.size() - 1);
        assertThat(testUsuarioExtra.getTelefone()).isEqualTo(DEFAULT_TELEFONE);

        // Validate the UsuarioExtra in Elasticsearch
        UsuarioExtra usuarioExtraEs = usuarioExtraSearchRepository.findOne(testUsuarioExtra.getId());
        assertThat(usuarioExtraEs).isEqualToComparingFieldByField(testUsuarioExtra);
    }

    @Test
    @Transactional
    public void createUsuarioExtraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuarioExtraRepository.findAll().size();

        // Create the UsuarioExtra with an existing ID
        usuarioExtra.setId(1L);
        UsuarioExtraDTO usuarioExtraDTO = usuarioExtraMapper.toDto(usuarioExtra);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuarioExtraMockMvc.perform(post("/api/usuario-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioExtraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UsuarioExtra in the database
        List<UsuarioExtra> usuarioExtraList = usuarioExtraRepository.findAll();
        assertThat(usuarioExtraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUsuarioExtras() throws Exception {
        // Initialize the database
        usuarioExtraRepository.saveAndFlush(usuarioExtra);

        // Get all the usuarioExtraList
        restUsuarioExtraMockMvc.perform(get("/api/usuario-extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarioExtra.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE.toString())));
    }

    @Test
    @Transactional
    public void getUsuarioExtra() throws Exception {
        // Initialize the database
        usuarioExtraRepository.saveAndFlush(usuarioExtra);

        // Get the usuarioExtra
        restUsuarioExtraMockMvc.perform(get("/api/usuario-extras/{id}", usuarioExtra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usuarioExtra.getId().intValue()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUsuarioExtra() throws Exception {
        // Get the usuarioExtra
        restUsuarioExtraMockMvc.perform(get("/api/usuario-extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarioExtra() throws Exception {
        // Initialize the database
        usuarioExtraRepository.saveAndFlush(usuarioExtra);
        usuarioExtraSearchRepository.save(usuarioExtra);
        int databaseSizeBeforeUpdate = usuarioExtraRepository.findAll().size();

        // Update the usuarioExtra
        UsuarioExtra updatedUsuarioExtra = usuarioExtraRepository.findOne(usuarioExtra.getId());
        updatedUsuarioExtra
            .telefone(UPDATED_TELEFONE);
        UsuarioExtraDTO usuarioExtraDTO = usuarioExtraMapper.toDto(updatedUsuarioExtra);

        restUsuarioExtraMockMvc.perform(put("/api/usuario-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioExtraDTO)))
            .andExpect(status().isOk());

        // Validate the UsuarioExtra in the database
        List<UsuarioExtra> usuarioExtraList = usuarioExtraRepository.findAll();
        assertThat(usuarioExtraList).hasSize(databaseSizeBeforeUpdate);
        UsuarioExtra testUsuarioExtra = usuarioExtraList.get(usuarioExtraList.size() - 1);
        assertThat(testUsuarioExtra.getTelefone()).isEqualTo(UPDATED_TELEFONE);

        // Validate the UsuarioExtra in Elasticsearch
        UsuarioExtra usuarioExtraEs = usuarioExtraSearchRepository.findOne(testUsuarioExtra.getId());
        assertThat(usuarioExtraEs).isEqualToComparingFieldByField(testUsuarioExtra);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarioExtra() throws Exception {
        int databaseSizeBeforeUpdate = usuarioExtraRepository.findAll().size();

        // Create the UsuarioExtra
        UsuarioExtraDTO usuarioExtraDTO = usuarioExtraMapper.toDto(usuarioExtra);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUsuarioExtraMockMvc.perform(put("/api/usuario-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioExtraDTO)))
            .andExpect(status().isCreated());

        // Validate the UsuarioExtra in the database
        List<UsuarioExtra> usuarioExtraList = usuarioExtraRepository.findAll();
        assertThat(usuarioExtraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUsuarioExtra() throws Exception {
        // Initialize the database
        usuarioExtraRepository.saveAndFlush(usuarioExtra);
        usuarioExtraSearchRepository.save(usuarioExtra);
        int databaseSizeBeforeDelete = usuarioExtraRepository.findAll().size();

        // Get the usuarioExtra
        restUsuarioExtraMockMvc.perform(delete("/api/usuario-extras/{id}", usuarioExtra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean usuarioExtraExistsInEs = usuarioExtraSearchRepository.exists(usuarioExtra.getId());
        assertThat(usuarioExtraExistsInEs).isFalse();

        // Validate the database is empty
        List<UsuarioExtra> usuarioExtraList = usuarioExtraRepository.findAll();
        assertThat(usuarioExtraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchUsuarioExtra() throws Exception {
        // Initialize the database
        usuarioExtraRepository.saveAndFlush(usuarioExtra);
        usuarioExtraSearchRepository.save(usuarioExtra);

        // Search the usuarioExtra
        restUsuarioExtraMockMvc.perform(get("/api/_search/usuario-extras?query=id:" + usuarioExtra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarioExtra.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuarioExtra.class);
        UsuarioExtra usuarioExtra1 = new UsuarioExtra();
        usuarioExtra1.setId(1L);
        UsuarioExtra usuarioExtra2 = new UsuarioExtra();
        usuarioExtra2.setId(usuarioExtra1.getId());
        assertThat(usuarioExtra1).isEqualTo(usuarioExtra2);
        usuarioExtra2.setId(2L);
        assertThat(usuarioExtra1).isNotEqualTo(usuarioExtra2);
        usuarioExtra1.setId(null);
        assertThat(usuarioExtra1).isNotEqualTo(usuarioExtra2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuarioExtraDTO.class);
        UsuarioExtraDTO usuarioExtraDTO1 = new UsuarioExtraDTO();
        usuarioExtraDTO1.setId(1L);
        UsuarioExtraDTO usuarioExtraDTO2 = new UsuarioExtraDTO();
        assertThat(usuarioExtraDTO1).isNotEqualTo(usuarioExtraDTO2);
        usuarioExtraDTO2.setId(usuarioExtraDTO1.getId());
        assertThat(usuarioExtraDTO1).isEqualTo(usuarioExtraDTO2);
        usuarioExtraDTO2.setId(2L);
        assertThat(usuarioExtraDTO1).isNotEqualTo(usuarioExtraDTO2);
        usuarioExtraDTO1.setId(null);
        assertThat(usuarioExtraDTO1).isNotEqualTo(usuarioExtraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(usuarioExtraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(usuarioExtraMapper.fromId(null)).isNull();
    }
}
