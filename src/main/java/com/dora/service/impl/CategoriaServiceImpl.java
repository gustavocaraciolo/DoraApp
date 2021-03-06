package com.dora.service.impl;

import com.dora.service.CategoriaService;
import com.dora.domain.Categoria;
import com.dora.repository.CategoriaRepository;
import com.dora.repository.search.CategoriaSearchRepository;
import com.dora.service.dto.CategoriaDTO;
import com.dora.service.mapper.CategoriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Categoria.
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{

    private final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final CategoriaRepository categoriaRepository;

    private final CategoriaMapper categoriaMapper;

    private final CategoriaSearchRepository categoriaSearchRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper, CategoriaSearchRepository categoriaSearchRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.categoriaSearchRepository = categoriaSearchRepository;
    }

    /**
     * Save a categoria.
     *
     * @param categoriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        log.debug("Request to save Categoria : {}", categoriaDTO);
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria = categoriaRepository.save(categoria);
        CategoriaDTO result = categoriaMapper.toDto(categoria);
        categoriaSearchRepository.save(categoria);
        return result;
    }

    /**
     *  Get all the categorias.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categorias");
        return categoriaRepository.findAll(pageable)
            .map(categoriaMapper::toDto);
    }


    /**
     *  get all the categorias where Produto is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<CategoriaDTO> findAllWhereProdutoIsNull() {
        log.debug("Request to get all categorias where Produto is null");
        return StreamSupport
            .stream(categoriaRepository.findAll().spliterator(), false)
            .filter(categoria -> categoria.getProduto() == null)
            .map(categoriaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one categoria by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO findOne(Long id) {
        log.debug("Request to get Categoria : {}", id);
        Categoria categoria = categoriaRepository.findOne(id);
        return categoriaMapper.toDto(categoria);
    }

    /**
     *  Delete the  categoria by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.delete(id);
        categoriaSearchRepository.delete(id);
    }

    /**
     * Search for the categoria corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Categorias for query {}", query);
        Page<Categoria> result = categoriaSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(categoriaMapper::toDto);
    }
}
