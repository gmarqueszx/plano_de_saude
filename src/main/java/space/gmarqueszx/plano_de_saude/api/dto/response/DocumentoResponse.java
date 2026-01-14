package space.gmarqueszx.plano_de_saude.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DocumentoResponse {
    private Long id;
    private String tipoDocumento;
    private OffsetDateTime dataInclusao;
    private OffsetDateTime dataAtualizacao;
    private UsuarioResponse usuario;
}
