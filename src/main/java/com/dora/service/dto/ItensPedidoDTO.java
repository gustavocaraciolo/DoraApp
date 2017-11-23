package com.dora.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ItensPedido entity.
 */
public class ItensPedidoDTO implements Serializable {

    private Long id;

    private BigDecimal desconto;

    private Long produtoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItensPedidoDTO itensPedidoDTO = (ItensPedidoDTO) o;
        if(itensPedidoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itensPedidoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItensPedidoDTO{" +
            "id=" + getId() +
            ", desconto='" + getDesconto() + "'" +
            "}";
    }
}
