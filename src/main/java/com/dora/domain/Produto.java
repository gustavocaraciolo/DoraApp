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
 * A Produto.
 */
@Entity
@Table(name = "produto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario", precision=10, scale=2)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_em_estoque")
    private Integer quantidadeEmEstoque;

    @Column(name = "quantidade_pedidos")
    private Integer quantidadePedidos;

    @OneToOne
    @JoinColumn(unique = true)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItensPedido> itensPedidos = new HashSet<>();

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

    public Produto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Produto precoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public Produto quantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        return this;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Integer getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public Produto quantidadePedidos(Integer quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
        return this;
    }

    public void setQuantidadePedidos(Integer quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public Produto itensPedidos(Set<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
        return this;
    }

    public Produto addItensPedido(ItensPedido itensPedido) {
        this.itensPedidos.add(itensPedido);
        itensPedido.setProduto(this);
        return this;
    }

    public Produto removeItensPedido(ItensPedido itensPedido) {
        this.itensPedidos.remove(itensPedido);
        itensPedido.setProduto(null);
        return this;
    }

    public void setItensPedidos(Set<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
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
        Produto produto = (Produto) o;
        if (produto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produto{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", precoUnitario='" + getPrecoUnitario() + "'" +
            ", quantidadeEmEstoque='" + getQuantidadeEmEstoque() + "'" +
            ", quantidadePedidos='" + getQuantidadePedidos() + "'" +
            "}";
    }
}
