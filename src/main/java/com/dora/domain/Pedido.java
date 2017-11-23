package com.dora.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Pedido.
 */
@Entity
@Table(name = "pedido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido")
    private ZonedDateTime dataPedido;

    @ManyToOne(optional = false)
    @NotNull
    private UsuarioExtra usuarioExtra;

    @ManyToOne
    private ItensPedido itensPedido;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataPedido() {
        return dataPedido;
    }

    public Pedido dataPedido(ZonedDateTime dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public void setDataPedido(ZonedDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public UsuarioExtra getUsuarioExtra() {
        return usuarioExtra;
    }

    public Pedido usuarioExtra(UsuarioExtra usuarioExtra) {
        this.usuarioExtra = usuarioExtra;
        return this;
    }

    public void setUsuarioExtra(UsuarioExtra usuarioExtra) {
        this.usuarioExtra = usuarioExtra;
    }

    public ItensPedido getItensPedido() {
        return itensPedido;
    }

    public Pedido itensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
        return this;
    }

    public void setItensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
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
        Pedido pedido = (Pedido) o;
        if (pedido.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + getId() +
            ", dataPedido='" + getDataPedido() + "'" +
            "}";
    }
}
