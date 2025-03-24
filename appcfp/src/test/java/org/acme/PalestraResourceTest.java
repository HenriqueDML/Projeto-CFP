package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class PalestraResourceTest {

    @Test
    public void testGetAllPalestrasEndpoint() {
        given()
                .when().get("/palestras")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    public void testPostNovaPalestra() {
        String json = """
            {
              "titulo": "Testando Quarkus",
              "resumo": "palestra teste",
              "nomeAutor": "Henrique",
              "email": "henrique@gmail.com"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/palestras")
                .then()
                .statusCode(200)
                .body("titulo", is("Testando Quarkus"));
    }
}
