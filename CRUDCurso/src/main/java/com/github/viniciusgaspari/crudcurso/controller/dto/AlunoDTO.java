package com.github.viniciusgaspari.crudcurso.controller.dto;

import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoDTO(

        UUID id,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2 ,max = 100, message = "campo fora do tamanho padrão")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String nacionalidade

        )
{

    public AlunoEntity mapearAluno(){

        AlunoEntity aluno = new AlunoEntity();

        aluno.setNomeAluno(this.nome);
        aluno.setDataNascimento(this.dataNascimento);
        aluno.setNacionalidade(this.nacionalidade);

        return aluno;

    }

}
