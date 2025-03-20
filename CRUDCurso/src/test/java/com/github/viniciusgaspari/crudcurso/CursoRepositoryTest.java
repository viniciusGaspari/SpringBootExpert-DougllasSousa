package com.github.viniciusgaspari.crudcurso;

import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import com.github.viniciusgaspari.crudcurso.model.CursoEntity;
import com.github.viniciusgaspari.crudcurso.repository.AlunoRepository;
import com.github.viniciusgaspari.crudcurso.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void cadastrarCurso(){

        AlunoEntity aluno = new AlunoEntity();
        aluno.setNomeAluno("Joao");
        aluno.setDataNascimento(LocalDate.of(2003, 10, 6));
        aluno.setNacionalidade("Brasileiro");
        alunoRepository.save(aluno);

        CursoEntity curso = new CursoEntity();
        curso.setNomeCurso("Inglês");
        curso.setPreco(new BigDecimal(900));
        curso.setAluno(aluno);
        cursoRepository.save(curso);

        List<CursoEntity> cursos = new ArrayList<>();
        cursos.add(curso);
        aluno.setCursos(cursos);











    }

}
