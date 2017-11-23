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
 * A Tag.
 */
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tag")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UsuarioExtra> usuarioExtras = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Tag nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<UsuarioExtra> getUsuarioExtras() {
        return usuarioExtras;
    }

    public Tag usuarioExtras(Set<UsuarioExtra> usuarioExtras) {
        this.usuarioExtras = usuarioExtras;
        return this;
    }

    public Tag addUsuarioExtra(UsuarioExtra usuarioExtra) {
        this.usuarioExtras.add(usuarioExtra);
        usuarioExtra.setTag(this);
        return this;
    }

    public Tag removeUsuarioExtra(UsuarioExtra usuarioExtra) {
        this.usuarioExtras.remove(usuarioExtra);
        usuarioExtra.setTag(null);
        return this;
    }

    public void setUsuarioExtras(Set<UsuarioExtra> usuarioExtras) {
        this.usuarioExtras = usuarioExtras;
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
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
