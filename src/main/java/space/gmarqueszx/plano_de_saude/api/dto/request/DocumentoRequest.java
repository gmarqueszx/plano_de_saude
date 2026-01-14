package space.gmarqueszx.plano_de_saude.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import space.gmarqueszx.plano_de_saude.api.dto.request.id.UsuarioId;

@Getter
@Setter
public class DocumentoRequest {
    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String descricao;
}
