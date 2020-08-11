package com.rodrigo.springtdd.repository.helper;

import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class PessaRepositoryImpl implements PessoaRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pessoa> pesquisar(PessoaFiltro filtro) {
        final StringBuilder sb = new StringBuilder();
        sb.append(" SELECT p FROM Pessoa p WHERE 1=1 ");
        Query query = entityManager.createQuery(sb.toString(), Pessoa.class);
        return query.getResultList();
    }












}
