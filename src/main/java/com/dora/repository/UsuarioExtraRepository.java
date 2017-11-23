package com.dora.repository;

import com.dora.domain.UsuarioExtra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UsuarioExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioExtraRepository extends JpaRepository<UsuarioExtra, Long> {

}
