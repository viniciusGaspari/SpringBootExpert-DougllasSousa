package io.github.viniciusgaspari.GamesLibraryApi.repository;

import io.github.viniciusgaspari.GamesLibraryApi.model.Genero;
import io.github.viniciusgaspari.GamesLibraryApi.model.JogoEntity;
import io.github.viniciusgaspari.GamesLibraryApi.model.Nacionalidade;
import io.github.viniciusgaspari.GamesLibraryApi.model.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class JogoRepositoryTest {

    @Autowired
    JogosRepository jogosRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void salvarJogo(){

        JogoEntity jogo = new JogoEntity();

        jogo.setNomeJogo("Minecraft");
        jogo.setGenero(Genero.AVENTURA);
        jogo.setDataLancamento(LocalDate.of(2010, 2, 25));
        jogo.setPreco(BigDecimal.valueOf(98));

        UsuarioEntity usuario = usuarioRepository.findById(UUID.fromString("f35aa145-7ea7-4774-904d-e54838a52c4e")).orElse(null);

        jogo.setUsuario(usuario);

        jogosRepository.save(jogo);

    }

    @Test
    void salvarJogoCascade(){

        JogoEntity jogo = new JogoEntity();

        jogo.setNomeJogo("Minecraft");
        jogo.setGenero(Genero.AVENTURA);
        jogo.setDataLancamento(LocalDate.of(2010, 2, 25));
        jogo.setPreco(BigDecimal.valueOf(98));

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("João");
        usuario.setDataNascimento(LocalDate.of(2000, 9, 28));
        usuario.setNacionalidade(Nacionalidade.ALEMA);

        jogo.setUsuario(usuario);

        jogosRepository.save(jogo);

    }

    @Test
    void atualizarUsuarioJogo(){
        JogoEntity jogoParaAtualizar = jogosRepository
                .findById(UUID.fromString("3c0c3e8e-95b3-40db-821d-69c2b7d13f43"))
                .orElse(null);

        var maria = usuarioRepository.findById(UUID.fromString("7db0ab0a-d13b-4210-a898-4b48958f4e3b")).orElse(null);

        jogoParaAtualizar.setUsuario(maria);

        jogosRepository.save(jogoParaAtualizar);

    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("3c0c3e8e-95b3-40db-821d-69c2b7d13f43");
        jogosRepository.deleteById(id);
    }

    @Test
    void buscarJogoTest (){

        UUID id = UUID.fromString("e809b364-c39b-42b2-8991-e9aa631e25b9");

        JogoEntity jogo = jogosRepository.findById(id).orElse(null);

        System.out.println(jogo.getNomeJogo());
       // System.out.println(jogo.getUsuario().getNome());

    }

    @Test
    void pequisarPorNomeJogoTest(){
        List<JogoEntity> byNomeJogo = jogosRepository.findByNomeJogo("God Of War 2018");
        byNomeJogo.forEach(System.out::println);

    }

    @Test
    void pequisarPorNomeGeneroTest(){
        List<JogoEntity> byNomeGenero = jogosRepository.findByNomeJogo(String.valueOf(Genero.AVENTURA));
        byNomeGenero.forEach(System.out::println);

    }
    @Test
    void findByNomeJogoAndPreco(){

        var preco = BigDecimal.valueOf(199);

        List<JogoEntity> byNomePreco = jogosRepository.findByNomeJogoAndPreco("Red Dead Redemption", preco);
        byNomePreco.forEach(System.out::println);

    }

    @Test
    void findByNomeJogoOrGenero(){

        var preco = BigDecimal.valueOf(199);

        List<JogoEntity> byNomePreco = jogosRepository.findByNomeJogoOrGenero("Red Dead Redemption", Genero.AVENTURA);
        byNomePreco.forEach(System.out::println);

    }

    @Test
    void listarJogosComQueryJPQL(){

        var resultado = jogosRepository.listarTodosOrdenadoAndPreco();
        resultado.forEach(System.out::println);

    }

    @Test
    void listarUsuariosDosJogos(){

        var resultado = jogosRepository.listarUsuariosDosJogos();
        resultado.forEach(System.out::println);

    }

    @Test
    void listarNomesJogos(){

        var resultado = jogosRepository.listarNomesJogos();
        resultado.forEach(System.out::println);

    }

    @Test
    void listarGenerosUsuariosBrasileiros(){

        var resultado = jogosRepository.listarGenerosUsuariosBrasileiros();
        resultado.forEach(System.out::println);

    }

//    @Test
//    void findByGenero(){
//
//        var resultado = jogosRepository.findByGenero(Genero.AVENTURA, "dataLancamento");
//        resultado.forEach(System.out::println);
//
//    }

    @Test
    void findByGeneroPositionalParameters(){

        var resultado = jogosRepository.findByGeneroPositionalParameters(Genero.AVENTURA, "dataLancamento");
        resultado.forEach(System.out::println);

    }

//    @Test
//    void deleteByGeneroTest(){
//        jogosRepository.deleteByGenero(Genero.AVENTURA);
//    }

    @Test
    void updateDataLancamento(){
        jogosRepository.updateDataLancamento
                (LocalDate.of(2019, 2, 2), Genero.ACAO);
    }

}
