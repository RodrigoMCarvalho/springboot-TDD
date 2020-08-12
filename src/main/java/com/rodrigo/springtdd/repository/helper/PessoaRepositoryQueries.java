package com.rodrigo.springtdd.repository.helper;

import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PessoaRepositoryQueries {

    List<Pessoa> filtrar(PessoaFiltro filtro);
}
