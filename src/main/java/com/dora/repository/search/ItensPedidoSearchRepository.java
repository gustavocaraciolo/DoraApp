package com.dora.repository.search;

import com.dora.domain.ItensPedido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ItensPedido entity.
 */
public interface ItensPedidoSearchRepository extends ElasticsearchRepository<ItensPedido, Long> {
}
