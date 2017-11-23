package com.dora.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dora.service.UsuarioExtraService;
import com.dora.web.rest.errors.BadRequestAlertException;
import com.dora.web.rest.util.HeaderUtil;
import com.dora.web.rest.util.PaginationUtil;
import com.dora.service.dto.UsuarioExtraDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing UsuarioExtra.
 */
@RestController
@RequestMapping("/api")
public class UsuarioExtraResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioExtraResource.class);

    private static final String ENTITY_NAME = "usuarioExtra";

    private final UsuarioExtraService usuarioExtraService;

    public UsuarioExtraResource(UsuarioExtraService usuarioExtraService) {
        this.usuarioExtraService = usuarioExtraService;
    }

    /**
     * POST  /usuario-extras : Create a new usuarioExtra.
     *
     * @param usuarioExtraDTO the usuarioExtraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioExtraDTO, or with status 400 (Bad Request) if the usuarioExtra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usuario-extras")
    @Timed
    public ResponseEntity<UsuarioExtraDTO> createUsuarioExtra(@Valid @RequestBody UsuarioExtraDTO usuarioExtraDTO) throws URISyntaxException {
        log.debug("REST request to save UsuarioExtra : {}", usuarioExtraDTO);
        if (usuarioExtraDTO.getId() != null) {
            throw new BadRequestAlertException("A new usuarioExtra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsuarioExtraDTO result = usuarioExtraService.save(usuarioExtraDTO);
        return ResponseEntity.created(new URI("/api/usuario-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuario-extras : Updates an existing usuarioExtra.
     *
     * @param usuarioExtraDTO the usuarioExtraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioExtraDTO,
     * or with status 400 (Bad Request) if the usuarioExtraDTO is not valid,
     * or with status 500 (Internal Server Error) if the usuarioExtraDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usuario-extras")
    @Timed
    public ResponseEntity<UsuarioExtraDTO> updateUsuarioExtra(@Valid @RequestBody UsuarioExtraDTO usuarioExtraDTO) throws URISyntaxException {
        log.debug("REST request to update UsuarioExtra : {}", usuarioExtraDTO);
        if (usuarioExtraDTO.getId() == null) {
            return createUsuarioExtra(usuarioExtraDTO);
        }
        UsuarioExtraDTO result = usuarioExtraService.save(usuarioExtraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usuarioExtraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuario-extras : get all the usuarioExtras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of usuarioExtras in body
     */
    @GetMapping("/usuario-extras")
    @Timed
    public ResponseEntity<List<UsuarioExtraDTO>> getAllUsuarioExtras(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of UsuarioExtras");
        Page<UsuarioExtraDTO> page = usuarioExtraService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/usuario-extras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /usuario-extras/:id : get the "id" usuarioExtra.
     *
     * @param id the id of the usuarioExtraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioExtraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/usuario-extras/{id}")
    @Timed
    public ResponseEntity<UsuarioExtraDTO> getUsuarioExtra(@PathVariable Long id) {
        log.debug("REST request to get UsuarioExtra : {}", id);
        UsuarioExtraDTO usuarioExtraDTO = usuarioExtraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usuarioExtraDTO));
    }

    /**
     * DELETE  /usuario-extras/:id : delete the "id" usuarioExtra.
     *
     * @param id the id of the usuarioExtraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usuario-extras/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsuarioExtra(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioExtra : {}", id);
        usuarioExtraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/usuario-extras?query=:query : search for the usuarioExtra corresponding
     * to the query.
     *
     * @param query the query of the usuarioExtra search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/usuario-extras")
    @Timed
    public ResponseEntity<List<UsuarioExtraDTO>> searchUsuarioExtras(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of UsuarioExtras for query {}", query);
        Page<UsuarioExtraDTO> page = usuarioExtraService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/usuario-extras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
