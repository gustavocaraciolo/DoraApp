package com.dora.service.mapper;

import com.dora.domain.*;
import com.dora.service.dto.ItensPedidoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItensPedido and its DTO ItensPedidoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProdutoMapper.class})
public interface ItensPedidoMapper extends EntityMapper<ItensPedidoDTO, ItensPedido> {

    @Mapping(source = "produto.id", target = "produtoId")
    ItensPedidoDTO toDto(ItensPedido itensPedido); 

    @Mapping(target = "pedidos", ignore = true)
    @Mapping(source = "produtoId", target = "produto")
    ItensPedido toEntity(ItensPedidoDTO itensPedidoDTO);

    default ItensPedido fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setId(id);
        return itensPedido;
    }
}
