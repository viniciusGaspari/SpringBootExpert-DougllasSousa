package com.github.viniciusgaspari.crudcurso.controller;

import com.github.viniciusgaspari.crudcurso.controller.dto.AlunoDTO;
import com.github.viniciusgaspari.crudcurso.controller.dto.ErroResposta;
import com.github.viniciusgaspari.crudcurso.exceptions.OperacaoNaoPermitidaException;
import com.github.viniciusgaspari.crudcurso.exceptions.RegistroDuplicadoException;
import com.github.viniciusgaspari.crudcurso.model.AlunoEntity;
import com.github.viniciusgaspari.crudcurso.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

//    public AlunoController(AlunoService alunoService) {
//        this.alunoService = alunoService;
//    }

    @PostMapping
    public ResponseEntity<Object> salvarAluno(@RequestBody @Valid AlunoDTO alunoDTO){

        try {


            AlunoEntity aluno = alunoDTO.mapearAluno();

            alunoService.salvarAluno(aluno);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();

            return ResponseEntity.created(location).build();

        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAluno(@PathVariable("id") UUID id){

        Optional<AlunoEntity> alunoEncontrado = alunoService.buscarAlunoPorId(id);

        if(alunoEncontrado.isPresent()){

            AlunoEntity alunoDados = alunoEncontrado.get();

            AlunoDTO dto = new AlunoDTO(alunoDados.getId(),alunoDados.getNomeAluno(), alunoDados.getDataNascimento(), alunoDados.getNacionalidade());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable("id") UUID id){

        try{

            Optional<AlunoEntity> alunoEncontrado = alunoService.buscarAlunoPorId(id);

            if(alunoEncontrado.isEmpty()){

                return ResponseEntity.notFound().build();

            }

            alunoService.deletarPorUsuario(alunoEncontrado.get());
            return ResponseEntity.noContent().build();

        } catch(OperacaoNaoPermitidaException e) {

            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }


    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){

            List<AlunoEntity> resultado = alunoService.pesquisaByExample(nome, nacionalidade);

            List<AlunoDTO> alunoPesquisado = resultado.stream().map(
                    aluno -> new AlunoDTO(
                            aluno.getId(),
                            aluno.getNomeAluno(),
                            aluno.getDataNascimento(),
                            aluno.getNacionalidade())).collect(Collectors.toList());

            return ResponseEntity.ok(alunoPesquisado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(required = true) String id, @RequestBody @Valid AlunoDTO dto){

        try {

            var idAluno = UUID.fromString(id);

            Optional<AlunoEntity> alunoOptional = alunoService.buscarAlunoPorId(idAluno);

            if(alunoOptional.isEmpty()){

                return ResponseEntity.notFound().build();

            }

            var alunoDadosNovos = alunoOptional.get();

            alunoDadosNovos.setNomeAluno(dto.nome());
            alunoDadosNovos.setNacionalidade(dto.nacionalidade());
            alunoDadosNovos.setDataNascimento(dto.dataNascimento());

            alunoService.atualizarAlunoDados(alunoDadosNovos);

            return ResponseEntity.noContent().build();

        } catch(RegistroDuplicadoException e) {

            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }



}

