package com.rodrigo.springtdd.repository.helper;

import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PessoaRepositoryQueries {

    @Bean
    List<Pessoa> filtrar(PessoaFiltro filtro);
}
