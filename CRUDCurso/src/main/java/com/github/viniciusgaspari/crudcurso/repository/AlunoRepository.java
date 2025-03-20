package com.github.viniciusgaspari.crudcurso.repository;

import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, UUID> {

    List<AlunoEntity> findByNomeAluno(String nome);

    List<AlunoEntity> findByNacionalidade(String nacionalidade);

    List<AlunoEntity> findByNomeAlunoAndNacionalidade(String nome, String nacionalidade);

    Optional<AlunoEntity> findByNomeAlunoAndDataNascimentoAndNacionalidade(
            String nomeAluno, LocalDate dataNascimento, String nacionalidade
    );

}
