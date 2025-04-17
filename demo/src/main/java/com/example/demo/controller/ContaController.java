package com.example.demo.controller;

import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@EnableMethodSecurity
@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Conta salvar(@RequestBody Conta conta){
        return service.salvar(conta);
    }

}
