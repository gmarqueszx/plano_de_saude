package space.gmarqueszx.plano_de_saude.api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import space.gmarqueszx.plano_de_saude.api.dto.request.UsuarioRequest;
import space.gmarqueszx.plano_de_saude.api.dto.response.DocumentoResponse;
import space.gmarqueszx.plano_de_saude.api.dto.response.UsuarioResponse;
import space.gmarqueszx.plano_de_saude.domain.model.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper{
    UsuarioResponse toDto(Usuario usuario);
    Usuario toEntity(UsuarioRequest usuarioRequest);
    List<UsuarioResponse> toCollectionDto(List<Usuario> usuarioList);

    @AfterMapping
    default void vincularDocumentos(@MappingTarget Usuario usuario) {
        if (usuario.getDocumentos() != null) {
            usuario.getDocumentos().forEach(doc -> doc.setUsuario(usuario));
        }
    }
}
