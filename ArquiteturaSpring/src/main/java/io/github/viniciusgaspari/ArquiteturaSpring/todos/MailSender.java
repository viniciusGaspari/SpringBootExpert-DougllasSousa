package io.github.viniciusgaspari.ArquiteturaSpring.todos;


import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String menasgem){

        System.out.println("Enviado email: " + menasgem);

    }

}
