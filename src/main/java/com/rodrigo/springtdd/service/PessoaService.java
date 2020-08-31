package com.rodrigo.springtdd.service;

import com.rodrigo.springtdd.exception.CpfNotFoundException;
import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.exception.UnicidadeCPFException;
import com.rodrigo.springtdd.exception.UnicidadeTelefoneException;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.model.Telefone;

import java.util.List;

public interface PessoaService {

    Pessoa salvar(Pessoa pessoa) throws UnicidadeTelefoneException, UnicidadeCPFException;
    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNotFoundException;
    Pessoa buscarPorCpf(String cpf) throws CpfNotFoundException;
    List<Pessoa> buscarTodos();
}