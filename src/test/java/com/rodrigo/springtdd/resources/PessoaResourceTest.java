package com.rodrigo.springtdd.resources;

import com.rodrigo.springtdd.SpringTddApplicationTests;
import io.restassured.RestAssured;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class PessoaResourceTest extends SpringTddApplicationTests {

    @Test
    public void deveProcurarPessoaPorDddENumeroTelefone() {
        RestAssured.given()
                .pathParam("ddd", "86")
                .pathParam("numero","35006330")
            .get("/pessoas/{ddd}/{numero}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body();
    }
}
