package com.dora.service.impl;

import com.dora.service.ItensPedidoService;
import com.dora.domain.ItensPedido;
import com.dora.repository.ItensPedidoRepository;
import com.dora.repository.search.ItensPedidoSearchRepository;
import com.dora.service.dto.ItensPedidoDTO;
import com.dora.service.mapper.ItensPedidoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ItensPedido.
 */
@Service
@Transactional
public class ItensPedidoServiceImpl implements ItensPedidoService{

    private final Logger log = LoggerFactory.getLogger(ItensPedidoServiceImpl.class);

    private final ItensPedidoRepository itensPedidoRepository;

    private final ItensPedidoMapper itensPedidoMapper;

    private final ItensPedidoSearchRepository itensPedidoSearchRepository;

    public ItensPedidoServiceImpl(ItensPedidoRepository itensPedidoRepository, ItensPedidoMapper itensPedidoMapper, ItensPedidoSearchRepository itensPedidoSearchRepository) {
        this.itensPedidoRepository = itensPedidoRepository;
        this.itensPedidoMapper = itensPedidoMapper;
        this.itensPedidoSearchRepository = itensPedidoSearchRepository;
    }

    /**
     * Save a itensPedido.
     *
     * @param itensPedidoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItensPedidoDTO save(ItensPedidoDTO itensPedidoDTO) {
        log.debug("Request to save ItensPedido : {}", itensPedidoDTO);
        ItensPedido itensPedido = itensPedidoMapper.toEntity(itensPedidoDTO);
        itensPedido = itensPedidoRepository.save(itensPedido);
        ItensPedidoDTO result = itensPedidoMapper.toDto(itensPedido);
        itensPedidoSearchRepository.save(itensPedido);
        return result;
    }

    /**
     *  Get all the itensPedidos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItensPedidoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItensPedidos");
        return itensPedidoRepository.findAll(pageable)
            .map(itensPedidoMapper::toDto);
    }

    /**
     *  Get one itensPedido by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItensPedidoDTO findOne(Long id) {
        log.debug("Request to get ItensPedido : {}", id);
        ItensPedido itensPedido = itensPedidoRepository.findOne(id);
        return itensPedidoMapper.toDto(itensPedido);
    }

    /**
     *  Delete the  itensPedido by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItensPedido : {}", id);
        itensPedidoRepository.delete(id);
        itensPedidoSearchRepository.delete(id);
    }

    /**
     * Search for the itensPedido corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItensPedidoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ItensPedidos for query {}", query);
        Page<ItensPedido> result = itensPedidoSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(itensPedidoMapper::toDto);
    }
}
