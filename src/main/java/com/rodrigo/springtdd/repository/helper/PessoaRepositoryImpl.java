package com.rodrigo.springtdd.repository.helper;

import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PessoaRepositoryImpl implements PessoaRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pessoa> filtrar(PessoaFiltro filtro) {
        final StringBuilder sb = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sb.append(" SELECT p FROM Pessoa p WHERE 1=1 ");

        preencherNome(filtro, sb, params);
        preencherCpf(filtro, sb, params);

        Query query = entityManager.createQuery(sb.toString(), Pessoa.class);
        obterParametros(params, query);
        return query.getResultList();
    }

    private void preencherCpf(PessoaFiltro filtro, StringBuilder sb, Map<String, Object> params) {
        if(StringUtils.hasText(filtro.getCpf())) {
            sb.append(" AND p.cpf LIKE : cpf");
            params.put("cpf", "%" + filtro.getCpf() + "%");
        }
    }

    private void preencherNome(PessoaFiltro filtro, StringBuilder sb, Map<String, Object> params) {
        if(StringUtils.hasText(filtro.getNome())) {
            sb.append(" AND p.nome LIKE : nome");
            params.put("nome", "%" + filtro.getNome() + "%");
        }
    }

    private void obterParametros(Map<String, Object> params, Query query) {
        for(Map.Entry<String, Object> param: params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
    }


}
