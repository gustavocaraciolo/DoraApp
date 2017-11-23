package com.dora.service.mapper;

import com.dora.domain.*;
import com.dora.service.dto.PedidoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pedido and its DTO PedidoDTO.
 */
@Mapper(componentModel = "spring", uses = {UsuarioExtraMapper.class, ItensPedidoMapper.class})
public interface PedidoMapper extends EntityMapper<PedidoDTO, Pedido> {

    @Mapping(source = "usuarioExtra.id", target = "usuarioExtraId")
    @Mapping(source = "itensPedido.id", target = "itensPedidoId")
    PedidoDTO toDto(Pedido pedido); 

    @Mapping(source = "usuarioExtraId", target = "usuarioExtra")
    @Mapping(source = "itensPedidoId", target = "itensPedido")
    Pedido toEntity(PedidoDTO pedidoDTO);

    default Pedido fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pedido pedido = new Pedido();
        pedido.setId(id);
        return pedido;
    }
}
