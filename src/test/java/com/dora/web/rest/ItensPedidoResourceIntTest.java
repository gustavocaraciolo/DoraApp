package com.dora.web.rest;

import com.dora.DoraApp;

import com.dora.domain.ItensPedido;
import com.dora.domain.Produto;
import com.dora.repository.ItensPedidoRepository;
import com.dora.service.ItensPedidoService;
import com.dora.repository.search.ItensPedidoSearchRepository;
import com.dora.service.dto.ItensPedidoDTO;
import com.dora.service.mapper.ItensPedidoMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static com.dora.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ItensPedidoResource REST controller.
 *
 * @see ItensPedidoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoraApp.class)
public class ItensPedidoResourceIntTest {

    private static final BigDecimal DEFAULT_DESCONTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESCONTO = new BigDecimal(2);

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Autowired
    private ItensPedidoMapper itensPedidoMapper;

    @Autowired
    private ItensPedidoService itensPedidoService;

    @Autowired
    private ItensPedidoSearchRepository itensPedidoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItensPedidoMockMvc;

    private ItensPedido itensPedido;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItensPedidoResource itensPedidoResource = new ItensPedidoResource(itensPedidoService);
        this.restItensPedidoMockMvc = MockMvcBuilders.standaloneSetup(itensPedidoResource)
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
    public static ItensPedido createEntity(EntityManager em) {
        ItensPedido itensPedido = new ItensPedido()
            .desconto(DEFAULT_DESCONTO);
        // Add required entity
        Produto produto = ProdutoResourceIntTest.createEntity(em);
        em.persist(produto);
        em.flush();
        itensPedido.setProduto(produto);
        return itensPedido;
    }

    @Before
    public void initTest() {
        itensPedidoSearchRepository.deleteAll();
        itensPedido = createEntity(em);
    }

    @Test
    @Transactional
    public void createItensPedido() throws Exception {
        int databaseSizeBeforeCreate = itensPedidoRepository.findAll().size();

        // Create the ItensPedido
        ItensPedidoDTO itensPedidoDTO = itensPedidoMapper.toDto(itensPedido);
        restItensPedidoMockMvc.perform(post("/api/itens-pedidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itensPedidoDTO)))
            .andExpect(status().isCreated());

        // Validate the ItensPedido in the database
        List<ItensPedido> itensPedidoList = itensPedidoRepository.findAll();
        assertThat(itensPedidoList).hasSize(databaseSizeBeforeCreate + 1);
        ItensPedido testItensPedido = itensPedidoList.get(itensPedidoList.size() - 1);
        assertThat(testItensPedido.getDesconto()).isEqualTo(DEFAULT_DESCONTO);

        // Validate the ItensPedido in Elasticsearch
        ItensPedido itensPedidoEs = itensPedidoSearchRepository.findOne(testItensPedido.getId());
        assertThat(itensPedidoEs).isEqualToComparingFieldByField(testItensPedido);
    }

    @Test
    @Transactional
    public void createItensPedidoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itensPedidoRepository.findAll().size();

        // Create the ItensPedido with an existing ID
        itensPedido.setId(1L);
        ItensPedidoDTO itensPedidoDTO = itensPedidoMapper.toDto(itensPedido);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItensPedidoMockMvc.perform(post("/api/itens-pedidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itensPedidoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItensPedido in the database
        List<ItensPedido> itensPedidoList = itensPedidoRepository.findAll();
        assertThat(itensPedidoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllItensPedidos() throws Exception {
        // Initialize the database
        itensPedidoRepository.saveAndFlush(itensPedido);

        // Get all the itensPedidoList
        restItensPedidoMockMvc.perform(get("/api/itens-pedidos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itensPedido.getId().intValue())))
            .andExpect(jsonPath("$.[*].desconto").value(hasItem(DEFAULT_DESCONTO.intValue())));
    }

    @Test
    @Transactional
    public void getItensPedido() throws Exception {
        // Initialize the database
        itensPedidoRepository.saveAndFlush(itensPedido);

        // Get the itensPedido
        restItensPedidoMockMvc.perform(get("/api/itens-pedidos/{id}", itensPedido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itensPedido.getId().intValue()))
            .andExpect(jsonPath("$.desconto").value(DEFAULT_DESCONTO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItensPedido() throws Exception {
        // Get the itensPedido
        restItensPedidoMockMvc.perform(get("/api/itens-pedidos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItensPedido() throws Exception {
        // Initialize the database
        itensPedidoRepository.saveAndFlush(itensPedido);
        itensPedidoSearchRepository.save(itensPedido);
        int databaseSizeBeforeUpdate = itensPedidoRepository.findAll().size();

        // Update the itensPedido
        ItensPedido updatedItensPedido = itensPedidoRepository.findOne(itensPedido.getId());
        updatedItensPedido
            .desconto(UPDATED_DESCONTO);
        ItensPedidoDTO itensPedidoDTO = itensPedidoMapper.toDto(updatedItensPedido);

        restItensPedidoMockMvc.perform(put("/api/itens-pedidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itensPedidoDTO)))
            .andExpect(status().isOk());

        // Validate the ItensPedido in the database
        List<ItensPedido> itensPedidoList = itensPedidoRepository.findAll();
        assertThat(itensPedidoList).hasSize(databaseSizeBeforeUpdate);
        ItensPedido testItensPedido = itensPedidoList.get(itensPedidoList.size() - 1);
        assertThat(testItensPedido.getDesconto()).isEqualTo(UPDATED_DESCONTO);

        // Validate the ItensPedido in Elasticsearch
        ItensPedido itensPedidoEs = itensPedidoSearchRepository.findOne(testItensPedido.getId());
        assertThat(itensPedidoEs).isEqualToComparingFieldByField(testItensPedido);
    }

    @Test
    @Transactional
    public void updateNonExistingItensPedido() throws Exception {
        int databaseSizeBeforeUpdate = itensPedidoRepository.findAll().size();

        // Create the ItensPedido
        ItensPedidoDTO itensPedidoDTO = itensPedidoMapper.toDto(itensPedido);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItensPedidoMockMvc.perform(put("/api/itens-pedidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itensPedidoDTO)))
            .andExpect(status().isCreated());

        // Validate the ItensPedido in the database
        List<ItensPedido> itensPedidoList = itensPedidoRepository.findAll();
        assertThat(itensPedidoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItensPedido() throws Exception {
        // Initialize the database
        itensPedidoRepository.saveAndFlush(itensPedido);
        itensPedidoSearchRepository.save(itensPedido);
        int databaseSizeBeforeDelete = itensPedidoRepository.findAll().size();

        // Get the itensPedido
        restItensPedidoMockMvc.perform(delete("/api/itens-pedidos/{id}", itensPedido.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean itensPedidoExistsInEs = itensPedidoSearchRepository.exists(itensPedido.getId());
        assertThat(itensPedidoExistsInEs).isFalse();

        // Validate the database is empty
        List<ItensPedido> itensPedidoList = itensPedidoRepository.findAll();
        assertThat(itensPedidoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchItensPedido() throws Exception {
        // Initialize the database
        itensPedidoRepository.saveAndFlush(itensPedido);
        itensPedidoSearchRepository.save(itensPedido);

        // Search the itensPedido
        restItensPedidoMockMvc.perform(get("/api/_search/itens-pedidos?query=id:" + itensPedido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itensPedido.getId().intValue())))
            .andExpect(jsonPath("$.[*].desconto").value(hasItem(DEFAULT_DESCONTO.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItensPedido.class);
        ItensPedido itensPedido1 = new ItensPedido();
        itensPedido1.setId(1L);
        ItensPedido itensPedido2 = new ItensPedido();
        itensPedido2.setId(itensPedido1.getId());
        assertThat(itensPedido1).isEqualTo(itensPedido2);
        itensPedido2.setId(2L);
        assertThat(itensPedido1).isNotEqualTo(itensPedido2);
        itensPedido1.setId(null);
        assertThat(itensPedido1).isNotEqualTo(itensPedido2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItensPedidoDTO.class);
        ItensPedidoDTO itensPedidoDTO1 = new ItensPedidoDTO();
        itensPedidoDTO1.setId(1L);
        ItensPedidoDTO itensPedidoDTO2 = new ItensPedidoDTO();
        assertThat(itensPedidoDTO1).isNotEqualTo(itensPedidoDTO2);
        itensPedidoDTO2.setId(itensPedidoDTO1.getId());
        assertThat(itensPedidoDTO1).isEqualTo(itensPedidoDTO2);
        itensPedidoDTO2.setId(2L);
        assertThat(itensPedidoDTO1).isNotEqualTo(itensPedidoDTO2);
        itensPedidoDTO1.setId(null);
        assertThat(itensPedidoDTO1).isNotEqualTo(itensPedidoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itensPedidoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itensPedidoMapper.fromId(null)).isNull();
    }
}
