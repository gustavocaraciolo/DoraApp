package com.dora.service.mapper;

import com.dora.domain.*;
import com.dora.service.dto.ProdutoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Produto and its DTO ProdutoDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface ProdutoMapper extends EntityMapper<ProdutoDTO, Produto> {

    @Mapping(source = "categoria.id", target = "categoriaId")
    ProdutoDTO toDto(Produto produto); 

    @Mapping(source = "categoriaId", target = "categoria")
    @Mapping(target = "itensPedidos", ignore = true)
    Produto toEntity(ProdutoDTO produtoDTO);

    default Produto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(id);
        return produto;
    }
}
