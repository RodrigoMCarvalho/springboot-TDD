package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.model.Pessoa;

import java.util.Optional;

public interface PessoaRepository {

    Pessoa save(Pessoa pessoa);
    Optional<Pessoa> findByCPF(String cpf);
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String telefone);
    Pessoa findByTelefone(String telefone);
}
