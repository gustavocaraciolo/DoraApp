package com.dora.service.mapper;

import com.dora.domain.*;
import com.dora.service.dto.UsuarioExtraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UsuarioExtra and its DTO UsuarioExtraDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TagMapper.class})
public interface UsuarioExtraMapper extends EntityMapper<UsuarioExtraDTO, UsuarioExtra> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "tag.id", target = "tagId")
    UsuarioExtraDTO toDto(UsuarioExtra usuarioExtra); 

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "tagId", target = "tag")
    @Mapping(target = "pedidos", ignore = true)
    UsuarioExtra toEntity(UsuarioExtraDTO usuarioExtraDTO);

    default UsuarioExtra fromId(Long id) {
        if (id == null) {
            return null;
        }
        UsuarioExtra usuarioExtra = new UsuarioExtra();
        usuarioExtra.setId(id);
        return usuarioExtra;
    }
}
