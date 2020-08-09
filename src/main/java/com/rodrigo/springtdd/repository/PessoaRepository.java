package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE 1=1")
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String telefone);
}
