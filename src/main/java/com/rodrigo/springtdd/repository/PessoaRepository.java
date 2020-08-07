package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCPF(String cpf);
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String telefone);
    Pessoa findByTelefone(String telefone);
}
