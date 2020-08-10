package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p JOIN p.telefones tele WHERE tele.ddd = :ddd AND tele.numero = :numero")
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(@Param("ddd") String ddd, @Param("numero") String numero);
}
