package space.gmarqueszx.plano_de_saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gmarqueszx.plano_de_saude.api.dto.response.DocumentoResponse;
import space.gmarqueszx.plano_de_saude.api.mapper.DocumentoMapper;
import space.gmarqueszx.plano_de_saude.domain.service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos")
public class DocumentosController {
    private final DocumentoService service;
    private final DocumentoMapper mapper;

    @Autowired
    public DocumentosController(DocumentoService service, DocumentoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<DocumentoResponse>> buscarPorIdDoUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                mapper.toCollectionDto(service.listarPorIdUsuario(usuarioId))
        );
    }
}
