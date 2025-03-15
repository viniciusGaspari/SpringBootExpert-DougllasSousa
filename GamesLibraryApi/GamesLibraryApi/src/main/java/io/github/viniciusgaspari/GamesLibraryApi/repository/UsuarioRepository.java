package io.github.viniciusgaspari.GamesLibraryApi.repository;

import io.github.viniciusgaspari.GamesLibraryApi.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
