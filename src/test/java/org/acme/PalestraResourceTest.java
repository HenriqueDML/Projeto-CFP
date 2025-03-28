package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
public class PalestraResourceTest {

    @Test
    public void testPostNovaPalestra() {
        String json = """
            {
              "titulo": "Testando com Quarkus",
              "resumo": "Um exemplo de palestra",
              "nomeAutor": "Henrique",
              "email": "henrique@example.com"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/palestras")
                .then()
                .statusCode(200)
                .body("titulo", is("Testando com Quarkus"));
    }

    @Test
    public void testValidacaoEmailInvalido() {
        String json = """
            {
              "titulo": "Título inválido",
              "resumo": "Resumo válido",
              "nomeAutor": "Autor",
              "email": "email-invalido"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/palestras")
                .then()
                .statusCode(500); // Espera erro de validação do Bean Validation
    }

    @Test
    public void testGetPalestrasComFiltro() {
        // Insere uma palestra antes de filtrar
        String json = """
            {
              "titulo": "Palestra Filtrável",
              "resumo": "Teste de filtro",
              "nomeAutor": "Fulano",
              "email": "fulano@email.com"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/palestras")
                .then()
                .statusCode(200);

        // Agora realiza o filtro
        given()
                .queryParam("titulo", "Filtrável")
                .when()
                .get("/palestras/filtro")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testAtualizacaoPalestra() {
        // Cria uma palestra
        String json = """
            {
              "titulo": "Original",
              "resumo": "Resumo",
              "nomeAutor": "Autor",
              "email": "autor@email.com"
            }
        """;

        String palestraId = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/palestras")
                .then()
                .statusCode(200)
                .extract().path("palestraId");

        // Atualiza o título
        String atualizado = """
            {
              "titulo": "Atualizado",
              "resumo": "Resumo",
              "nomeAutor": "Autor",
              "email": "autor@email.com"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(atualizado)
                .when()
                .put("/palestras/" + palestraId)
                .then()
                .statusCode(200)
                .body("titulo", is("Atualizado"));
    }

    @Test
    public void testExcluirPalestra() {
        // Cria uma palestra para excluir
        String json = """
            {
              "titulo": "A ser excluído",
              "resumo": "Resumo",
              "nomeAutor": "Fulano",
              "email": "fulano@email.com"
            }
        """;

        String palestraId = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/palestras")
                .then()
                .statusCode(200)
                .extract().path("palestraId");

        // Exclui
        given()
                .when()
                .delete("/palestras/" + palestraId)
                .then()
                .statusCode(204);
    }
}
