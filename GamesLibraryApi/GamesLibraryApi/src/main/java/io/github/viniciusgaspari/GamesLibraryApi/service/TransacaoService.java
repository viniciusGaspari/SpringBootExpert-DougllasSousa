package io.github.viniciusgaspari.GamesLibraryApi.service;

import io.github.viniciusgaspari.GamesLibraryApi.model.Genero;
import io.github.viniciusgaspari.GamesLibraryApi.model.JogoEntity;
import io.github.viniciusgaspari.GamesLibraryApi.model.Nacionalidade;
import io.github.viniciusgaspari.GamesLibraryApi.model.UsuarioEntity;
import io.github.viniciusgaspari.GamesLibraryApi.repository.JogosRepository;
import io.github.viniciusgaspari.GamesLibraryApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JogosRepository jogosRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){

        JogoEntity jogo = jogosRepository.findById(UUID.fromString("adb03e8f-ed98-4e17-b49b-ba5d61753799")).orElse(null);

        jogo.setDataLancamento(LocalDate.of(2024, 6, 1));

    }

    @Transactional
    public void executar(){

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("TesteFrancisco");
        usuario.setDataNascimento(LocalDate.of(1045, 2, 16));
        usuario.setNacionalidade(Nacionalidade.AMERICANA);

        usuarioRepository.save(usuario);



        JogoEntity jogo = new JogoEntity();
        jogo.setNomeJogo("TesteFrancisco Jogo");
        jogo.setGenero(Genero.FPS);
        jogo.setDataLancamento(LocalDate.of(2021, 5, 12));
        jogo.setPreco(BigDecimal.valueOf(290));

        jogo.setUsuario(usuario);


        usuarioRepository.save(usuario);

        jogo.setUsuario(usuario);

        jogosRepository.save(jogo);

        if(usuario.getNome().equals("TesteFrancisco")){
            throw new RuntimeException("Rollback");
        }

    }

}
