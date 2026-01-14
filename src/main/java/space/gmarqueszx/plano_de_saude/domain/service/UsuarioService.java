package space.gmarqueszx.plano_de_saude.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.gmarqueszx.plano_de_saude.domain.exception.UsuarioInUseException;
import space.gmarqueszx.plano_de_saude.domain.exception.UsuarioNotFoundException;
import space.gmarqueszx.plano_de_saude.domain.model.Documento;
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

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() ->
                new UsuarioNotFoundException(usuarioId));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario atualizar(Long usuarioId,
                             Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(usuarioId);


        if (usuarioAtualizado.getNome() != null) {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
        }

        if (usuarioAtualizado.getTelefone() != null) {
            usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        }

        if (usuarioAtualizado.getDataNascimento() != null) {
            usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        }

        if (usuarioAtualizado.getDocumentos() != null) {
            List<Documento> documentosSalvos = usuarioExistente.getDocumentos();
            documentosSalvos.clear();
            documentosSalvos.addAll(usuarioAtualizado.getDocumentos());
            documentosSalvos.forEach(doc -> doc.setUsuario(usuarioAtualizado));
        }

        return salvar(usuarioExistente);
    }

    @Transactional
    public void deletar(Long usuarioId) {
        if (!repository.existsById(usuarioId)) {
            throw new UsuarioNotFoundException(usuarioId);
        }

        try {
            repository.deleteById(usuarioId);
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioInUseException(usuarioId);
        }
    }
}
