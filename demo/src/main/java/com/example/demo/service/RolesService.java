package com.example.demo.service;

import com.example.demo.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RolesService {

    private final RolesRepository repository;


}
