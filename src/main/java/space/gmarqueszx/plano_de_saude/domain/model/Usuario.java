package space.gmarqueszx.plano_de_saude.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String nome;

    private String telefone;

    @Column(name = "")
    private LocalDate dataNascimento;

    @CreationTimestamp
    @Column(name = "data_de_inclusao")
    private OffsetDateTime dataInclusao;

    @UpdateTimestamp
    @Column(name = "data_de_atualizacao")
    private OffsetDateTime dataAtualizacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Documento> documentos = new HashSet<>();
}
