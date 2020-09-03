package com.rodrigo.springtdd.service.impl;

import com.rodrigo.springtdd.exception.CpfNotFoundException;
import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.exception.UnicidadeCPFException;
import com.rodrigo.springtdd.exception.UnicidadeTelefoneException;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.model.Telefone;
import com.rodrigo.springtdd.repository.PessoaRepository;
import com.rodrigo.springtdd.service.PessoaService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) throws UnicidadeTelefoneException, UnicidadeCPFException {
        Optional<Pessoa> pessoaCPF = repository.findByCpf(pessoa.getCpf());
        if(pessoaCPF.isPresent()) {
            throw new UnicidadeCPFException();
        }

        String ddd = pessoa.getTelefones().get(0).getDdd();
        String numero = pessoa.getTelefones().get(0).getNumero();
        Optional<Pessoa> telefone = repository.findByTelefoneDddAndTelefoneNumero(ddd, numero);
        if(telefone.isPresent()) {
            throw new UnicidadeTelefoneException();
        }
        return repository.save(pessoa);
    }


    @Override
    public Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNotFoundException {
        Optional<Pessoa> optional = repository.findByTelefoneDddAndTelefoneNumero(telefone.getDdd(), telefone.getNumero());
        return optional.orElseThrow(() -> new TelefoneNotFoundException("Não existe pessoa com o telefone (" + telefone.getDdd() + ")" + telefone.getNumero()));
    }

    @Override
    public Pessoa buscarPorCpf(String cpf) throws CpfNotFoundException {
        Optional<Pessoa> optional = repository.findByCpf(cpf);
        return optional.orElseThrow(() -> new CpfNotFoundException("CPF inválido"));
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return repository.findAll();
    }

}
