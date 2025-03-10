package io.github.viniciusgaspari.ArquiteturaSpring;

import io.github.viniciusgaspari.ArquiteturaSpring.todos.MailSender;
import io.github.viniciusgaspari.ArquiteturaSpring.todos.TodoValidator;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;

public class ExemploInjecaoDependecia {
    public static void main(String[] args) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("Url");
        dataSource.setUsername("user");
        dataSource.setPassword("password");

//        Connection connection = dataSource.getConnection();

//        EntityManager entityManager = null;

//        TodoRepository repository = null; new SimpleJpaRepository<TodoEntity, Integer>(null, null);
//        TodoValidator todoValidator = new TodoValidator(repository);
//        MailSender sender = new MailSender();

//        BeanGerenciado beanGerenciado = new BeanGerenciado(null);
//        beanGerenciado.setValidator(todoValidator);
//
//        if(condicao == true){
//            beanGerenciado.setValidator();
//        }


    }
}
