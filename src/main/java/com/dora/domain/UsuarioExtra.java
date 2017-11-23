package com.dora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UsuarioExtra.
 */
@Entity
@Table(name = "usuario_extra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "usuarioextra")
public class UsuarioExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone")
    private String telefone;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    private Tag tag;

    @OneToMany(mappedBy = "usuarioExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pedido> pedidos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public UsuarioExtra telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public User getUser() {
        return user;
    }

    public UsuarioExtra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public UsuarioExtra tag(Tag tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public UsuarioExtra pedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public UsuarioExtra addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        pedido.setUsuarioExtra(this);
        return this;
    }

    public UsuarioExtra removePedido(Pedido pedido) {
        this.pedidos.remove(pedido);
        pedido.setUsuarioExtra(null);
        return this;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
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
        UsuarioExtra usuarioExtra = (UsuarioExtra) o;
        if (usuarioExtra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioExtra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioExtra{" +
            "id=" + getId() +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }
}
