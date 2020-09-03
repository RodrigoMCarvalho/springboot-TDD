package com.rodrigo.springtdd.resources;

import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.model.Telefone;
import com.rodrigo.springtdd.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{ddd}/{numero}")
    public ResponseEntity<Pessoa> buscarPorDddeTelefone(@PathVariable("ddd") String ddd,
                                                        @PathVariable("numero") String numero) throws TelefoneNotFoundException {
        Telefone telefone = new Telefone();
        telefone.setDdd(ddd);
        telefone.setNumero(numero);
        Pessoa pessoa = pessoaService.buscarPorTelefone(telefone);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<Pessoa>> buscarTodos(){
        return new ResponseEntity<List<Pessoa>>(pessoaService.buscarTodos(), HttpStatus.OK);
    }
}
