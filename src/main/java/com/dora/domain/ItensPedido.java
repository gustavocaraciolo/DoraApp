package com.dora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ItensPedido.
 */
@Entity
@Table(name = "itens_pedido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "itenspedido")
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desconto", precision=10, scale=2)
    private BigDecimal desconto;

    @OneToMany(mappedBy = "itensPedido")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pedido> pedidos = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Produto produto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public ItensPedido desconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public ItensPedido pedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public ItensPedido addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        pedido.setItensPedido(this);
        return this;
    }

    public ItensPedido removePedido(Pedido pedido) {
        this.pedidos.remove(pedido);
        pedido.setItensPedido(null);
        return this;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Produto getProduto() {
        return produto;
    }

    public ItensPedido produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItensPedido itensPedido = (ItensPedido) o;
        if (itensPedido.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itensPedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItensPedido{" +
            "id=" + getId() +
            ", desconto='" + getDesconto() + "'" +
            "}";
    }
}
