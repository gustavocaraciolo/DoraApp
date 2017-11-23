package com.dora.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dora.service.ItensPedidoService;
import com.dora.web.rest.errors.BadRequestAlertException;
import com.dora.web.rest.util.HeaderUtil;
import com.dora.web.rest.util.PaginationUtil;
import com.dora.service.dto.ItensPedidoDTO;
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
 * REST controller for managing ItensPedido.
 */
@RestController
@RequestMapping("/api")
public class ItensPedidoResource {

    private final Logger log = LoggerFactory.getLogger(ItensPedidoResource.class);

    private static final String ENTITY_NAME = "itensPedido";

    private final ItensPedidoService itensPedidoService;

    public ItensPedidoResource(ItensPedidoService itensPedidoService) {
        this.itensPedidoService = itensPedidoService;
    }

    /**
     * POST  /itens-pedidos : Create a new itensPedido.
     *
     * @param itensPedidoDTO the itensPedidoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itensPedidoDTO, or with status 400 (Bad Request) if the itensPedido has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/itens-pedidos")
    @Timed
    public ResponseEntity<ItensPedidoDTO> createItensPedido(@Valid @RequestBody ItensPedidoDTO itensPedidoDTO) throws URISyntaxException {
        log.debug("REST request to save ItensPedido : {}", itensPedidoDTO);
        if (itensPedidoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itensPedido cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItensPedidoDTO result = itensPedidoService.save(itensPedidoDTO);
        return ResponseEntity.created(new URI("/api/itens-pedidos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itens-pedidos : Updates an existing itensPedido.
     *
     * @param itensPedidoDTO the itensPedidoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itensPedidoDTO,
     * or with status 400 (Bad Request) if the itensPedidoDTO is not valid,
     * or with status 500 (Internal Server Error) if the itensPedidoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/itens-pedidos")
    @Timed
    public ResponseEntity<ItensPedidoDTO> updateItensPedido(@Valid @RequestBody ItensPedidoDTO itensPedidoDTO) throws URISyntaxException {
        log.debug("REST request to update ItensPedido : {}", itensPedidoDTO);
        if (itensPedidoDTO.getId() == null) {
            return createItensPedido(itensPedidoDTO);
        }
        ItensPedidoDTO result = itensPedidoService.save(itensPedidoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itensPedidoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itens-pedidos : get all the itensPedidos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of itensPedidos in body
     */
    @GetMapping("/itens-pedidos")
    @Timed
    public ResponseEntity<List<ItensPedidoDTO>> getAllItensPedidos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ItensPedidos");
        Page<ItensPedidoDTO> page = itensPedidoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itens-pedidos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itens-pedidos/:id : get the "id" itensPedido.
     *
     * @param id the id of the itensPedidoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itensPedidoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/itens-pedidos/{id}")
    @Timed
    public ResponseEntity<ItensPedidoDTO> getItensPedido(@PathVariable Long id) {
        log.debug("REST request to get ItensPedido : {}", id);
        ItensPedidoDTO itensPedidoDTO = itensPedidoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itensPedidoDTO));
    }

    /**
     * DELETE  /itens-pedidos/:id : delete the "id" itensPedido.
     *
     * @param id the id of the itensPedidoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/itens-pedidos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItensPedido(@PathVariable Long id) {
        log.debug("REST request to delete ItensPedido : {}", id);
        itensPedidoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/itens-pedidos?query=:query : search for the itensPedido corresponding
     * to the query.
     *
     * @param query the query of the itensPedido search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/itens-pedidos")
    @Timed
    public ResponseEntity<List<ItensPedidoDTO>> searchItensPedidos(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of ItensPedidos for query {}", query);
        Page<ItensPedidoDTO> page = itensPedidoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/itens-pedidos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
