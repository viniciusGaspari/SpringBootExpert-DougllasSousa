package com.github.viniciusgaspari.crudcurso.validator;

import com.github.viniciusgaspari.crudcurso.exceptions.RegistroDuplicadoException;
import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import com.github.viniciusgaspari.crudcurso.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlunoValidator {

    private final AlunoRepository alunoRepository;

//    public AlunoValidator(AlunoRepository alunoRepository) {
//        this.alunoRepository = alunoRepository;
//    }

    public void validar(AlunoEntity entity){

        if(existeAlunoCadastro(entity)){
            throw new RegistroDuplicadoException("Aluno já cadastrado!");
        }

    }

    private Boolean existeAlunoCadastro(AlunoEntity entity){
        Optional<AlunoEntity> alunoEncontrado = alunoRepository.findByNomeAlunoAndDataNascimentoAndNacionalidade(
                entity.getNomeAluno(), entity.getDataNascimento(), entity.getNacionalidade()
        );

        if(entity.getId() == null){
            return alunoEncontrado.isPresent();
        }

        return !entity.getId().equals(alunoEncontrado.get().getId()) && alunoEncontrado.isPresent();

    }

}
