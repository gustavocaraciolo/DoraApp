package com.dora.service.impl;

import com.dora.service.UsuarioExtraService;
import com.dora.domain.UsuarioExtra;
import com.dora.repository.UsuarioExtraRepository;
import com.dora.repository.search.UsuarioExtraSearchRepository;
import com.dora.service.dto.UsuarioExtraDTO;
import com.dora.service.mapper.UsuarioExtraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing UsuarioExtra.
 */
@Service
@Transactional
public class UsuarioExtraServiceImpl implements UsuarioExtraService{

    private final Logger log = LoggerFactory.getLogger(UsuarioExtraServiceImpl.class);

    private final UsuarioExtraRepository usuarioExtraRepository;

    private final UsuarioExtraMapper usuarioExtraMapper;

    private final UsuarioExtraSearchRepository usuarioExtraSearchRepository;

    public UsuarioExtraServiceImpl(UsuarioExtraRepository usuarioExtraRepository, UsuarioExtraMapper usuarioExtraMapper, UsuarioExtraSearchRepository usuarioExtraSearchRepository) {
        this.usuarioExtraRepository = usuarioExtraRepository;
        this.usuarioExtraMapper = usuarioExtraMapper;
        this.usuarioExtraSearchRepository = usuarioExtraSearchRepository;
    }

    /**
     * Save a usuarioExtra.
     *
     * @param usuarioExtraDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsuarioExtraDTO save(UsuarioExtraDTO usuarioExtraDTO) {
        log.debug("Request to save UsuarioExtra : {}", usuarioExtraDTO);
        UsuarioExtra usuarioExtra = usuarioExtraMapper.toEntity(usuarioExtraDTO);
        usuarioExtra = usuarioExtraRepository.save(usuarioExtra);
        UsuarioExtraDTO result = usuarioExtraMapper.toDto(usuarioExtra);
        usuarioExtraSearchRepository.save(usuarioExtra);
        return result;
    }

    /**
     *  Get all the usuarioExtras.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioExtraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UsuarioExtras");
        return usuarioExtraRepository.findAll(pageable)
            .map(usuarioExtraMapper::toDto);
    }

    /**
     *  Get one usuarioExtra by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UsuarioExtraDTO findOne(Long id) {
        log.debug("Request to get UsuarioExtra : {}", id);
        UsuarioExtra usuarioExtra = usuarioExtraRepository.findOne(id);
        return usuarioExtraMapper.toDto(usuarioExtra);
    }

    /**
     *  Delete the  usuarioExtra by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UsuarioExtra : {}", id);
        usuarioExtraRepository.delete(id);
        usuarioExtraSearchRepository.delete(id);
    }

    /**
     * Search for the usuarioExtra corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioExtraDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UsuarioExtras for query {}", query);
        Page<UsuarioExtra> result = usuarioExtraSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(usuarioExtraMapper::toDto);
    }
}
