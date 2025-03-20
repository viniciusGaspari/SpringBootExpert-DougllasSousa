package com.github.viniciusgaspari.crudcurso.repository;

import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import com.github.viniciusgaspari.crudcurso.model.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

    boolean existsByAluno(AlunoEntity entity);

}
