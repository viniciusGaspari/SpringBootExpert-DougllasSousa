package com.github.viniciusgaspari.crudcurso.service;

import com.github.viniciusgaspari.crudcurso.exceptions.OperacaoNaoPermitidaException;
import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import com.github.viniciusgaspari.crudcurso.repository.AlunoRepository;
import com.github.viniciusgaspari.crudcurso.repository.CursoRepository;
import com.github.viniciusgaspari.crudcurso.validator.AlunoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoValidator validatorAluno;
    private final CursoRepository cursoRepository;

//    public AlunoService
//            (AlunoRepository alunoRepository, AlunoValidator validatorAluno, CursoRepository cursoRepository) {
//        this.alunoRepository = alunoRepository;
//        this.validatorAluno = validatorAluno;
//        this.cursoRepository = cursoRepository;
//    }

    public AlunoEntity salvarAluno(AlunoEntity entity){

        validatorAluno.validar(entity);
        return alunoRepository.save(entity);

    }

    public void atualizarAlunoDados(AlunoEntity entity){

        if(entity.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o ALUNO já esteja salvo na base");
        }

        validatorAluno.validar(entity);
        alunoRepository.save(entity);

    }

    public Optional<AlunoEntity> buscarAlunoPorId(UUID id) {

        return alunoRepository.findById(id);

    }

    public void deletarPorUsuario(AlunoEntity entity) {

        if(possuiCurso(entity)){
            throw new OperacaoNaoPermitidaException
                    ("Não é permitido excluir um Aluno que possui curso cadastrados!");
        }
        alunoRepository.delete(entity);

    }

    public List<AlunoEntity> pesquisar(String nome, String nacionalidade){

        if(nome != null && nacionalidade != null){
            alunoRepository.findByNomeAlunoAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null){
            alunoRepository.findByNomeAluno(nome);
        }

        if(nacionalidade != null){
            alunoRepository.findByNacionalidade(nacionalidade);
        }

        return alunoRepository.findAll();

    }

    public List<AlunoEntity> pesquisaByExample(String nome, String nacionalidade){

        var aluno = new AlunoEntity();

        aluno.setNomeAluno(nome);
        aluno.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "dataNascimento", "dataCadastro")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<AlunoEntity> alunoEntityExample = Example.of(aluno, matcher);
        return alunoRepository.findAll(alunoEntityExample);
    }

    public boolean possuiCurso(AlunoEntity entity){
        return cursoRepository.existsByAluno(entity);
    }

}
