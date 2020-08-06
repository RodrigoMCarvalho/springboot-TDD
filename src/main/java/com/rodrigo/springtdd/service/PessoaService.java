package com.rodrigo.springtdd.service;

import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.exception.UnicidadeCPFException;
import com.rodrigo.springtdd.exception.UnicidadeTelefoneException;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.model.Telefone;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa) throws UnicidadeTelefoneException, UnicidadeCPFException;

    Pessoa buscarProTelefone(Telefone telefone) throws TelefoneNotFoundException;
}
