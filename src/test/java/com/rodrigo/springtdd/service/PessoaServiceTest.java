package com.rodrigo.springtdd.service;

import com.rodrigo.springtdd.builders.TelefoneBuilder;
import com.rodrigo.springtdd.builders.PessoaBuilder;
import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.exception.UnicidadeCPFException;
import com.rodrigo.springtdd.exception.UnicidadeTelefoneException;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.repository.PessoaRepository;
import com.rodrigo.springtdd.service.impl.PessoaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    private static final String CPF = "12345678";
    private static final String DDD = "55";
    private static final String TELEFONE = "12345678";
    private static final String NOME = "Rodrigo Moreira";

    @MockBean
    private PessoaRepository pessoaRepository;

    private PessoaService pessoaService;
    private Pessoa pessoa;

    @Before
    public void setup() {
        pessoaService = new PessoaServiceImpl(pessoaRepository);
        pessoa = PessoaBuilder.umaPessoa().builder();
    }

    @Test
    public void deveSalvarPessoaNoRepository() throws Exception {
        pessoaService.salvar(pessoa);
        verify(pessoaRepository).save(pessoa);
    }

    @Test(expected = UnicidadeCPFException.class)
    public void naoDeveSalvarDuasPessoasComOMesmaCPF() throws Exception {
        when(pessoaRepository.findByCPF(CPF)).thenReturn(Optional.of(pessoa));
        pessoaService.salvar(pessoa);
    }

    @Test(expected = UnicidadeTelefoneException.class)
    public void naoDeveSalvarDuasPessoasComOMesmoTelefone() throws Exception {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD, TELEFONE)).thenReturn(Optional.of(pessoa));
        pessoaService.salvar(pessoa);
    }

    @Test
    public void deveBuscarPessoaPorTelefone() throws Exception {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD, TELEFONE)).thenReturn(Optional.of(pessoa));

        Pessoa pessoa = pessoaService.buscarProTelefone(TelefoneBuilder.umTelefone().builder());

        verify(pessoaRepository).findByTelefoneDddAndTelefoneNumero(DDD, TELEFONE);
        assertThat(pessoa).isNotNull();
        assertThat(pessoa.getNome()).isEqualTo(NOME);
        assertThat(pessoa.getCpf()).isEqualTo(CPF);
    }

    @Test(expected = TelefoneNotFoundException.class)
    public void deveLancarTelefoneException() throws Exception {
        pessoaService.buscarProTelefone(TelefoneBuilder.umTelefone().builder());
    }
}