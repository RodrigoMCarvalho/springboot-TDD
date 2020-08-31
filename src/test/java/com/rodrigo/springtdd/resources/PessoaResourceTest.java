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
                .body("codigo", Matchers.equalTo(3),
                        "nome", Matchers.equalTo("Cauê"),
                        "cpf", Matchers.equalTo("38767897100"));
    }
}
