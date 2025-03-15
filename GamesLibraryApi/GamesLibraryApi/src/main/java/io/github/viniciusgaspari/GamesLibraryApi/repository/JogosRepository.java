package io.github.viniciusgaspari.GamesLibraryApi.repository;

import io.github.viniciusgaspari.GamesLibraryApi.model.Genero;
import io.github.viniciusgaspari.GamesLibraryApi.model.JogoEntity;
import io.github.viniciusgaspari.GamesLibraryApi.model.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see JogoRepositoryTest
 */

public interface JogosRepository extends JpaRepository<JogoEntity, UUID> {

    // Query Method

    List<JogoEntity> findByUsuario(UsuarioEntity usuario);

    List<JogoEntity> findByNomeJogo(String nomeJogo);

    List<JogoEntity> findByNomeJogoAndPreco(String nomeJogo, BigDecimal preco);

    List<JogoEntity> findByNomeJogoOrGenero(String nomeJogo, Genero genero);

    List<JogoEntity> findByDataLancamentoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> Referencia as entidades e as propriedades
    @Query(" select j from JogoEntity as j order by j.nomeJogo, j.preco ")
    List<JogoEntity> listarTodosOrdenadoAndPreco();

    @Query(" select u from JogoEntity j join j.usuario u ")
    List<UsuarioEntity> listarUsuariosDosJogos();

    @Query(" select distinct j.nomeJogo from JogoEntity j ")
    List<String> listarNomesJogos();

    @Query("""
            select j.genero
            from JogoEntity j
            join j.usuario a
            where a.nacionalidade = 'BRASILEIRA'
            order by j.genero
            """)
    List<String> listarGenerosUsuariosBrasileiros();

    // named parameters
//    @Query(" select j from JogoEntity j where j.genero = :genero order by :paramOrdenacao ")
//    List<JogoEntity> findByGenero(
//            @Param(" genero ") Genero genero,
//           ao") String nomeDaPropriedade);
//    @Param("paramOrdernac")
    // positional parameteres
    @Query(" select j from JogoEntity j where j.genero = ?1 order by ?2 ")
    List<JogoEntity> findByGeneroPositionalParameters(
            Genero genero,
            String nomeDaPropriedade);

//    @Modifying
//    @Transactional
//    @Query(" delete from JogoEntity where genero = ?1 where j.dataLancamento = ?2")
//    void deleteByGenero(Genero genero);

    @Modifying
    @Transactional
    @Query("UPDATE JogoEntity j SET j.dataLancamento = ?1 where j.genero = ?2")
    void updateDataLancamento(LocalDate novaData, Genero genero);

}
