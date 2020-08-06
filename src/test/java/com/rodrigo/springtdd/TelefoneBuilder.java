package com.rodrigo.springtdd;

import com.rodrigo.springtdd.model.Telefone;

public class TelefoneBuilder {

    private static final String TELEFONE = "12345678";
    private static final String DDD = "55";

    private Telefone telefone;

    private TelefoneBuilder() {}

    public static TelefoneBuilder umTelefone() {
        TelefoneBuilder builder = new TelefoneBuilder();
        builder.telefone = new Telefone();
        builder.telefone.setNumero(TELEFONE);
        builder.telefone.setDdd(DDD);
        return builder;
    }

    public Telefone builder() {
        return this.telefone;
    }
}
