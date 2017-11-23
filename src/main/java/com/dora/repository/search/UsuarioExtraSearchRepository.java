package com.dora.repository.search;

import com.dora.domain.UsuarioExtra;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UsuarioExtra entity.
 */
public interface UsuarioExtraSearchRepository extends ElasticsearchRepository<UsuarioExtra, Long> {
}
