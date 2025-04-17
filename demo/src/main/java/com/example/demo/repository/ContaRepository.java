package com.example.demo.repository;

import com.example.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Conta findByLogin(String login);

}
