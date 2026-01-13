package space.gmarqueszx.plano_de_saude.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.gmarqueszx.plano_de_saude.domain.model.Documento;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByUsuarioId(Long id);
}
