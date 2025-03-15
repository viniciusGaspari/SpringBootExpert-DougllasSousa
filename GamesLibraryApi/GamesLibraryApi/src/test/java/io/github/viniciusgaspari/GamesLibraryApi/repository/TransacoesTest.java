package io.github.viniciusgaspari.GamesLibraryApi.repository;

import io.github.viniciusgaspari.GamesLibraryApi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    // commit -> confirmar as alterações
    // Rollback -> desfazer as alterações

    @Test
    void transacaoSimples(){

        transacaoService.executar();

    }

    @Test
    void transacaoEstadoManager(){

        transacaoService.atualizacaoSemAtualizar();

    }

}
