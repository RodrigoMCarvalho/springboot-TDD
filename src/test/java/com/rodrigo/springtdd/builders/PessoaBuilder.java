package com.rodrigo.springtdd.builders;

import com.rodrigo.springtdd.model.Pessoa;

import java.util.Arrays;

public class PessoaBuilder {

    private static final String NOME = "Rodrigo Moreira";
    private static final String CPF = "12345678";

    private Pessoa pessoa;

    private PessoaBuilder() {}

    public static PessoaBuilder umaPessoa() {
        PessoaBuilder builder = new PessoaBuilder();
        builder.pessoa = new Pessoa();
        builder.pessoa.setNome(NOME);
        builder.pessoa.setCpf(CPF);
        builder.pessoa.setTelefones(Arrays.asList(TelefoneBuilder.umTelefone().builder()));
        return builder;
    }

    public PessoaBuilder comNome(String nome) {
        pessoa.setNome(nome);
        return this;
    }

    public PessoaBuilder comCPF(String cpf) {
        pessoa.setCpf(cpf);
        return this;
    }

    public Pessoa builder() {
        return this.pessoa;
    }
}
