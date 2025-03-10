package io.github.viniciusgaspari.ArquiteturaSpring.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository <TodoEntity, Integer> {

    boolean existsByDescricao(String descricao);

}
