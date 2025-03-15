package io.github.viniciusgaspari.GamesLibraryApi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "usuario")
@Table(name = "jogos")
public class JogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_jogo", nullable = false)
    private UUID id;

    @Column(name = "nome_jogo", nullable = false, length = 100)
    private String nomeJogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 100)
    private Genero genero;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "preco", nullable = false, precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne(
            //cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
}
