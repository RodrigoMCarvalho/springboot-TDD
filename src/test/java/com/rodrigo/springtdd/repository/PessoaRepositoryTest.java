package com.rodrigo.springtdd.repository;

import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations ="classpath:/application-test.properties")
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PessoaRepositoryTest {

    public static final String TELEFONE_INEXISTENTE = "99957014";
    
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

    @Test
    public void deveEncontrarPessoaPorDddENumeroDeTelefone() {
        Optional<Pessoa> pessoaOpt = sut.findByTelefoneDddAndTelefoneNumero("41", "999570146");
        Pessoa pessoa = pessoaOpt.get();

        assertThat(pessoaOpt.isPresent()).isTrue();
        assertThat(pessoa.getCodigo()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Iago");
        assertThat(pessoa.getCpf()).isEqualTo("86730543540");
    }

    @Test
    public void naoDeveEncontrarPessoaCujoDddETelefoneNaoEstejamCadastrados() {
        Optional<Pessoa> pessoaOpt = sut.findByTelefoneDddAndTelefoneNumero("01", "699570146");

        assertThat(pessoaOpt.isPresent()).isFalse();
    }

    @Test
    public void deveFiltrarPessoaPorParteDoNome() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setNome("a");

        List<Pessoa> pessoas = sut.filtrar(filtro);

        assertThat(pessoas.size()).isEqualTo(3);
    }

    @Test
    public void deveFiltrarPessoaPorParteDoCpf() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setCpf("78");

        List<Pessoa> pessoas = sut.filtrar(filtro);

        assertThat(pessoas.size()).isEqualTo(3);
    }

    @Test
    public void deveFiltrarPessoaPorFiltroComposto() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setCpf("78");
        filtro.setNome("a");

        List<Pessoa> pessoas = sut.filtrar(filtro);

        assertThat(pessoas.size()).isEqualTo(2);
    }

    @Test
    public void deveFiltrarPessoaPorDddDoTelefone() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setDdd("41");

        List<Pessoa> pessoas = sut.filtrar(filtro);

        assertThat(pessoas.size()).isEqualTo(1);
    }

    @Test
    public void deveFiltrarPessoaPorNumeroDoTelefone() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setTelefone(TELEFONE_INEXISTENTE);

        List<Pessoa> pessoas = sut.filtrar(filtro);

        assertThat(pessoas.size()).isZero();
    }
}
