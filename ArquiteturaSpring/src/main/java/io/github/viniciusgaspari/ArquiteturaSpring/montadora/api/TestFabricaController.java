package io.github.viniciusgaspari.ArquiteturaSpring.montadora.api;

import io.github.viniciusgaspari.ArquiteturaSpring.montadora.CarroStatus;
import io.github.viniciusgaspari.ArquiteturaSpring.montadora.Chave;
import io.github.viniciusgaspari.ArquiteturaSpring.montadora.HondaHRV;
import io.github.viniciusgaspari.ArquiteturaSpring.montadora.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class TestFabricaController {

    @Autowired
    @Turbo
    private Motor motor;

    @PostMapping
        public CarroStatus ligarCarro(@RequestBody Chave chave){

        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);

        }


    }


