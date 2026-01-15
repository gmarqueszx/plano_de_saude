package space.gmarqueszx.plano_de_saude.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoRequest {
    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String descricao;
}
