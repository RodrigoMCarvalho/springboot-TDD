package com.rodrigo.springtdd.exception;

public class CpfNotFoundException extends Exception {

    public CpfNotFoundException(String mensagem){
        super(mensagem);
    }

}
