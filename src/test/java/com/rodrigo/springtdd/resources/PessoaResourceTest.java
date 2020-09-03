package com.rodrigo.springtdd.resources;

import com.rodrigo.springtdd.SpringTddApplicationTests;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;

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
}
