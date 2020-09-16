package com.rodrigo.springtdd.resources;

import com.rodrigo.springtdd.exception.TelefoneNotFoundException;
import com.rodrigo.springtdd.exception.UnicidadeCPFException;
import com.rodrigo.springtdd.exception.UnicidadeTelefoneException;
import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import com.rodrigo.springtdd.model.Telefone;
import com.rodrigo.springtdd.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaService pessoaService;

    @Autowired
    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

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

    @GetMapping("/filtrar")
    public ResponseEntity<List<Pessoa>> buscarPorFiltro(@RequestBody PessoaFiltro filtro) {
        return new ResponseEntity<List<Pessoa>>(pessoaService.buscarPorFiltro(filtro), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa, HttpServletResponse response) throws UnicidadeCPFException, UnicidadeTelefoneException {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{ddd}/{numero}")
                .buildAndExpand(pessoa.getTelefones().get(0).getDdd(), pessoa.getTelefones().get(0).getNumero()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return new ResponseEntity<>(pessoaService.salvar(pessoa), HttpStatus.CREATED);
    }
}
