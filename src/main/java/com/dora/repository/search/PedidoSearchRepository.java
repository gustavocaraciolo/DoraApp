package com.dora.repository.search;

import com.dora.domain.Pedido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Pedido entity.
 */
public interface PedidoSearchRepository extends ElasticsearchRepository<Pedido, Long> {
}
