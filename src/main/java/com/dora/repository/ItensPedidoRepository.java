package com.dora.repository;

import com.dora.domain.ItensPedido;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItensPedido entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {

}
