package com.dora.service;

import com.dora.service.dto.UsuarioExtraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UsuarioExtra.
 */
public interface UsuarioExtraService {

    /**
     * Save a usuarioExtra.
     *
     * @param usuarioExtraDTO the entity to save
     * @return the persisted entity
     */
    UsuarioExtraDTO save(UsuarioExtraDTO usuarioExtraDTO);

    /**
     *  Get all the usuarioExtras.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<UsuarioExtraDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" usuarioExtra.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UsuarioExtraDTO findOne(Long id);

    /**
     *  Delete the "id" usuarioExtra.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the usuarioExtra corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<UsuarioExtraDTO> search(String query, Pageable pageable);
}
