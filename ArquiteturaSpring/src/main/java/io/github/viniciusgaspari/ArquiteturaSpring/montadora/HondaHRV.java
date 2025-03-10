package io.github.viniciusgaspari.ArquiteturaSpring.montadora;

import java.awt.*;

import static io.github.viniciusgaspari.ArquiteturaSpring.montadora.Montadora.*;

public class HondaHRV extends Carro  {

    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.black);
        setMontadora(Montadora.HONDA);
    }

}
