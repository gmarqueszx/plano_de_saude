package space.gmarqueszx.plano_de_saude.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gmarqueszx.plano_de_saude.domain.exception.UsuarioNotFoundException;
import space.gmarqueszx.plano_de_saude.domain.model.Usuario;
import space.gmarqueszx.plano_de_saude.domain.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    private List<Usuario> listar() {
        return repository.findAll();
    }

    private Usuario buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() ->
                new UsuarioNotFoundException(usuarioId));
    }
}
