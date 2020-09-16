package com.rodrigo.springtdd.resources;

import com.rodrigo.springtdd.SpringTddApplicationTests;
import com.rodrigo.springtdd.builders.PessoaBuilder;
import com.rodrigo.springtdd.builders.TelefoneBuilder;
import com.rodrigo.springtdd.filtro.PessoaFiltro;
import com.rodrigo.springtdd.model.Pessoa;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class PessoaResourceTest extends SpringTddApplicationTests {

    @Test
    public void deveProcurarPessoaPorDddENumeroTelefone() {
        RestAssured.given()
                .pathParam("ddd", "41")
                .pathParam("numero","999570146")
            .get("/pessoas/{ddd}/{numero}")
            .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("codigo", Matchers.equalTo(1),
                        "nome", Matchers.equalTo("Iago"),
                        "cpf", Matchers.equalTo("86730543540"));
    }

    @Test
    public void deveRetornarNaoEncontradoQuandoBuscarPessoaPorTelefoneInexistente() {
        RestAssured.given()
                .pathParam("ddd", "55")
                .pathParam("numero", "12345678")
            .get("/pessoas/{ddd}/{numero}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", Matchers.equalTo("Não existe pessoa com o telefone (55)12345678"));
    }

    @Test
    public void deveSalvarPessoa() {
        Pessoa pessoa = PessoaBuilder
                .umaPessoa()
                .comNome("Gustavo")
                .comCPF("97880403140")
                .build();
        pessoa.setTelefones(Arrays.asList(TelefoneBuilder.umTelefone().comDDD("55").build()));

        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(pessoa)
            .when()
            .post("/pessoas")
            .then()
                .log().headers()
            .and()
                .log().body()
            .and()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", Matchers.equalTo("http://localhost:" + porta + "/pessoas/55/12345678"))
                .body("codigo", Matchers.equalTo(6),
                        "nome", Matchers.equalTo("Gustavo"),
                        "cpf", Matchers.equalTo("97880403140"));

    }

    @Test
    public void naoDeveSalvarDuasPessoasComOMesmoCPF() {
        Pessoa pessoa = PessoaBuilder
                .umaPessoa()
                .comNome("Gustavo")
                .comCPF("72788740417")
                .build();
        pessoa.setTelefones(Arrays.asList(TelefoneBuilder.umTelefone().build()));

        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(pessoa)
            .when()
            .post("/pessoas")
                .then()
                    .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("erro", Matchers.equalTo("Já existe pessoa cadastrada com o CPF 72788740417"));

    }

    @Test
    public void deveFiltrarPeloNome() {
        PessoaFiltro filtro = new PessoaFiltro();
        filtro.setNome("a");

        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(filtro)
            .when()
            .get("/pessoas/filtrar")
            .then()
                .log().body()
            .and()
                .statusCode(HttpStatus.OK.value())
                .body("codigo", Matchers.containsInAnyOrder(1,3,5),
                        "nome", Matchers.containsInAnyOrder("Thiago", "Iago", "Cauê"),
                        "cpf", Matchers.containsInAnyOrder("72788740417","86730543540","38767897100"));

    }















}
