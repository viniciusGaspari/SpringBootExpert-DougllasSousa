package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ContaService contaService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Conta conta = contaService.obterPorLogin(login);

        if(conta == null){
            throw new UsernameNotFoundException("Não existe essa conta");
        }

        return User.builder()
                .username(conta.getLogin())
                .password(conta.getSenha())
                .roles(conta.getRole().getNome())
                .build();

    }
}
