package space.gmarqueszx.plano_de_saude.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private OffsetDateTime dataInclusao;
    private OffsetDateTime dataAtualizacao;
    private List<DocumentoResponse> documentos = new ArrayList<>();
}
