package space.gmarqueszx.plano_de_saude.api.mapper;

import org.mapstruct.Mapper;
import space.gmarqueszx.plano_de_saude.api.dto.response.DocumentoResponse;
import space.gmarqueszx.plano_de_saude.domain.model.Documento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    List<DocumentoResponse> toCollectionDto (List<Documento> documentos);
}
