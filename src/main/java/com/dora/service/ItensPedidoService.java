package com.dora.service;

import com.dora.service.dto.ItensPedidoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ItensPedido.
 */
public interface ItensPedidoService {

    /**
     * Save a itensPedido.
     *
     * @param itensPedidoDTO the entity to save
     * @return the persisted entity
     */
    ItensPedidoDTO save(ItensPedidoDTO itensPedidoDTO);

    /**
     *  Get all the itensPedidos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ItensPedidoDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" itensPedido.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ItensPedidoDTO findOne(Long id);

    /**
     *  Delete the "id" itensPedido.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the itensPedido corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ItensPedidoDTO> search(String query, Pageable pageable);
}
