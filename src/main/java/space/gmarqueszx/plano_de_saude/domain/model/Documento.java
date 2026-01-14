package space.gmarqueszx.plano_de_saude.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "documento")
public class Documento {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "tipo_do_documento")
    private String tipoDocumento;

    @NotBlank
    private String descricao;

    @CreationTimestamp
    @Column(name = "data_de_inclusao")
    private OffsetDateTime dataInclusao;

    @UpdateTimestamp
    @Column(name = "data_de_atualizacao")
    private OffsetDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
