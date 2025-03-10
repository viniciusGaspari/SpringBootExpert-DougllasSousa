package io.github.viniciusgaspari.ArquiteturaSpring;

import io.github.viniciusgaspari.ArquiteturaSpring.todos.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoAcessoEmail {

    @Autowired
    private AppProperties properties;

    public MailSender mailSender(){
        return null;
    }

}
