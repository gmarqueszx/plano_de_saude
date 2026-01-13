package space.gmarqueszx.plano_de_saude.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gmarqueszx.plano_de_saude.domain.model.Documento;
import space.gmarqueszx.plano_de_saude.domain.repository.DocumentoRepository;

import java.util.List;

@Service
public class DocumentoService {
    private final DocumentoRepository repository;

    @Autowired
    public DocumentoService(DocumentoRepository repository) {
        this.repository = repository;
    }

    public List<Documento> listarPorIdUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
