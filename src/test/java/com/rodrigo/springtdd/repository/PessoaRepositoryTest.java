package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.model.Pessoa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations ="classpath:/application-test.properties")
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository sut;

    @Test
    public void deveProcurarPessoaPorCPF() {
        Optional<Pessoa> pessoaCPF = sut.findByCpf("86730543540");
        Pessoa pessoa = pessoaCPF.get();

        assertThat(pessoaCPF.isPresent()).isTrue();
        assertThat(pessoa.getCodigo()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Iago");
        assertThat(pessoa.getCpf()).isEqualTo("86730543540");
    }

    @Test
    public void naoDeveLocalizarPessoaComCpfInexistente() {
        Optional<Pessoa> pessoaCPF = sut.findByCpf("8673054354");

        assertThat(pessoaCPF.isPresent()).isFalse();
    }
}