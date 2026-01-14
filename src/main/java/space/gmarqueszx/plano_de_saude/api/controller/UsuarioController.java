package space.gmarqueszx.plano_de_saude.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.gmarqueszx.plano_de_saude.api.dto.request.UsuarioRequest;
import space.gmarqueszx.plano_de_saude.api.dto.response.UsuarioResponse;
import space.gmarqueszx.plano_de_saude.api.mapper.UsuarioMapper;
import space.gmarqueszx.plano_de_saude.domain.model.Usuario;
import space.gmarqueszx.plano_de_saude.domain.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @Autowired
    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(
                mapper.toCollectionDto(
                        service.listar()
                )
        );
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                mapper.toDto(
                        service.buscarPorId(usuarioId)
                )
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid UsuarioRequest request) {
        Usuario entidade = mapper.toEntity(request);
        Usuario entidadeSalva = service.salvar(entidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(entidadeSalva)
        );
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long usuarioId,
                                                     @RequestBody @Valid UsuarioRequest request) {
        Usuario entidadeAtualizada = service.atualizar(usuarioId, mapper.toEntity(request));

        return ResponseEntity.ok(
                mapper.toDto(entidadeAtualizada)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{usuarioId}")
    public void deletar(@PathVariable Long usuarioId) {
        service.deletar(usuarioId);
    }
}
