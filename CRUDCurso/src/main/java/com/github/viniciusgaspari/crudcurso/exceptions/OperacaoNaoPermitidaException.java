package com.github.viniciusgaspari.crudcurso.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{

    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}
