package com.dora.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pedido entity.
 */
public class PedidoDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataPedido;

    private Long usuarioExtraId;

    private Long itensPedidoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(ZonedDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Long getUsuarioExtraId() {
        return usuarioExtraId;
    }

    public void setUsuarioExtraId(Long usuarioExtraId) {
        this.usuarioExtraId = usuarioExtraId;
    }

    public Long getItensPedidoId() {
        return itensPedidoId;
    }

    public void setItensPedidoId(Long itensPedidoId) {
        this.itensPedidoId = itensPedidoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PedidoDTO pedidoDTO = (PedidoDTO) o;
        if(pedidoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pedidoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
            "id=" + getId() +
            ", dataPedido='" + getDataPedido() + "'" +
            "}";
    }
}
