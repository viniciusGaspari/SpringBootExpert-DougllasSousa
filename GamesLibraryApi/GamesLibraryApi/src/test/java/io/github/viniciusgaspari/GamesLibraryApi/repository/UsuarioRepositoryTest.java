package io.github.viniciusgaspari.GamesLibraryApi.repository;

import io.github.viniciusgaspari.GamesLibraryApi.model.Genero;
import io.github.viniciusgaspari.GamesLibraryApi.model.JogoEntity;
import io.github.viniciusgaspari.GamesLibraryApi.model.Nacionalidade;
import io.github.viniciusgaspari.GamesLibraryApi.model.UsuarioEntity;
import io.github.viniciusgaspari.GamesLibraryApi.service.TransacaoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JogosRepository jogosRepository;



    @Test
    void adicionarUsuario(){

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Gabriel");
        usuario.setDataNascimento(LocalDate.of(2000, 9, 28));
        usuario.setNacionalidade(Nacionalidade.BRASILEIRA);

        usuarioRepository.save(usuario);

    }

    @Test
    void atualizarTest(){

        UUID id = UUID.fromString("83f2f522-fb5a-4cbe-b02e-22568973c3d6");

        Optional<UsuarioEntity> possivelUser = usuarioRepository.findById(id);

        if(possivelUser.isPresent()){

            UsuarioEntity usuarioEncontrado = possivelUser.get();

            System.out.println("Dados do autor: ");
            System.out.println(usuarioEncontrado);

            usuarioEncontrado.setDataNascimento(LocalDate.of(2000, 9, 29));

            usuarioRepository.save(usuarioEncontrado);

        }

    }

    @Test
    void listarTest(){

        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        usuarios.forEach(System.out::println);

    }

    @Test
    void deletarPorIdTest(){

        UUID id = UUID.fromString("83f2f522-fb5a-4cbe-b02e-22568973c3d6");

        Optional<UsuarioEntity> userExistente = usuarioRepository.findById(id);

        if(userExistente.isPresent()){

            UsuarioEntity usuarioEncontrado = userExistente.get();

            System.out.println("Dados do usuário encontrado: " + usuarioEncontrado);

            usuarioRepository.deleteById(id);

        }
    }

    @Test
    void deletarPorUsuarioTest(){

        UUID id = UUID.fromString("98cab5e8-a97a-4d10-a321-99c1b79f2ce3");
        var vinicius = usuarioRepository.findById(id).get();

        usuarioRepository.delete(vinicius);

    }

    @Test
    void countTest(){

        System.out.println("Contagem de usuarios: " + usuarioRepository.count());

    }

    @Test
    void salvarUserComJogoTest(){
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Antonio");
        usuario.setDataNascimento(LocalDate.of(1045, 2, 16));
        usuario.setNacionalidade(Nacionalidade.AMERICANA);
        usuarioRepository.save(usuario);

        JogoEntity jogo = new JogoEntity();
        jogo.setNomeJogo("Cyberpunk");
        jogo.setGenero(Genero.FPS);
        jogo.setDataLancamento(LocalDate.of(2021, 5, 12));
        jogo.setPreco(BigDecimal.valueOf(290));
        jogo.setUsuario(usuario);

        JogoEntity jogo2 = new JogoEntity();
        jogo2.setNomeJogo("Red Dead Redemption");
        jogo2.setGenero(Genero.AVENTURA);
        jogo2.setDataLancamento(LocalDate.of(2024, 11, 29));
        jogo2.setPreco(BigDecimal.valueOf(190));
        jogo2.setUsuario(usuario);



        usuario.setJogos(new ArrayList<>());
        usuario.getJogos().add(jogo);
        usuario.getJogos().add(jogo2);

        usuarioRepository.save(usuario);
//        jogosRepository.saveAll(jogo.getUsuario().getJogos());
    }

    @Test
    @Transactional
    void listarJogosUser(){

        UUID id = UUID.fromString("2f6571bb-209b-4a6d-a104-90392feeae2e");
        var antonio = usuarioRepository.findById(id).get();

        List<JogoEntity> jogoList = jogosRepository.findByUsuario(antonio);

        antonio.setJogos(jogoList);

        antonio.getJogos().forEach(System.out::println);

    }

}
