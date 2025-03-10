package io.github.viniciusgaspari.ArquiteturaSpring.montadora.configuration;

import io.github.viniciusgaspari.ArquiteturaSpring.montadora.Motor;
import io.github.viniciusgaspari.ArquiteturaSpring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "MotorAspirado")
    public Motor MotorAspirado(){
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-O");
        motor.setLitragem(2.0);
        motor.setTipoMotor(TipoMotor.ASPIRADO);

        return motor;

    }

    @Bean(name = "motorEletrico")
    @Primary
    public Motor motorEletrico(){

        var motor = new Motor();

        motor.setCavalos(110);
        motor.setCilindros(3);
        motor.setModelo("TH-4O");
        motor.setLitragem(1.4);
        motor.setTipoMotor(TipoMotor.ELETRICO);

        return motor;

    }

    @Bean(name = "motorTurbo")
    public Motor motorTurbo(){

        var motor = new Motor();

        motor.setCavalos(180);
        motor.setCilindros(4);
        motor.setModelo("XPTO-01");
        motor.setLitragem(1.5);
        motor.setTipoMotor(TipoMotor.TURBO);

        return motor;

    }

}
