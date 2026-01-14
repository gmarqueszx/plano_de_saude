package space.gmarqueszx.plano_de_saude.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    @Valid
    private List<DocumentoRequest> documentos = new ArrayList<>();
}
