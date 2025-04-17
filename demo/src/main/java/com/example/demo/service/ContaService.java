package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Roles;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder encoder;

    public Conta obterPorLogin(String login){
        return contaRepository.findByLogin(login);
    }

    public Conta salvar(Conta conta){
        conta.setSenha(encoder.encode(conta.getSenha()));
        conta.setRole(rolesRepository.findById(conta.getRole().getId()).get());
        return contaRepository.save(conta);
    }

}
