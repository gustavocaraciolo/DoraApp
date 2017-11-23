package com.dora.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UsuarioExtra entity.
 */
public class UsuarioExtraDTO implements Serializable {

    private Long id;

    private String telefone;

    private Long userId;

    private Long tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsuarioExtraDTO usuarioExtraDTO = (UsuarioExtraDTO) o;
        if(usuarioExtraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioExtraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioExtraDTO{" +
            "id=" + getId() +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }
}
