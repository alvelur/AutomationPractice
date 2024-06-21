package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.Is.is;

public class ApiTests {

    @BeforeAll
    static void setUpRestAssured(){
        RestAssured.baseURI = "https://automationexercise.com";
        RestAssured.basePath = "/api";
    }

    @Test
    void aSimpleGet(){
    given()
            .header("Accept", ContentType.JSON)
            .when()
            .get("productsList")
            .then()
            .statusCode(200)
            .contentType(startsWith("text/html"))
            .header("server", "cloudflare")
            .time(lessThan(5_000L))
            .body("products.find {it.id == 1}.name", is("Blue top"));

    }

}
