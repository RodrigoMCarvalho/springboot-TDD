package com.rodrigo.springtdd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PessoaExceptionHandler {

    @ExceptionHandler(TelefoneNotFoundException.class)
    public ResponseEntity<?> handlerTelefoneNaoEncontradoException(TelefoneNotFoundException e) {
        return new ResponseEntity<>(new RespostaErro(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnicidadeCPFException.class)
    public ResponseEntity<?> handlerUnicidadeCPFException(UnicidadeCPFException e) {
        return new ResponseEntity<>(new RespostaErro(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
