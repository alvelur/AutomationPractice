package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;



public class ApiTestsJM {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void loginTest(){

        given()
                .body("{\n" +
                        "    \"email\": \"" + "eve.holt@reqres.in" + "\",\n" +
                        "    \"password\": \"" + "cityslicka" + "\"\n" +
                        "}")
                .post("login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());

    }

    @Test
    public void getSingleUserTest(){
        given()
                .get("users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    public void deleteTest(){
        given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void patchTest(){
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"" + "morpheus" + "\",\n" +
                        "    \"job\": \"" + "zion resident" + "\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        assertThat(nameUpdated, equalTo("morpheus"));
    }
    @Test
    public void putUserTest(){
        String jobUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"" + "morpheus" + "\",\n" +
                        "    \"job\": \"" + "zion resident" + "\"\n" +
                        "}")
                .put("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("job");

        assertThat(jobUpdated, equalTo("zion resident"));
    }

    //How to get Headers, contentType and Response Code
    @Test
    public void getAllUsers(){

        Response response = given()
                .get("users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String contentType = response.contentType();

        assertThat(statusCode, equalTo(HttpStatus.SC_OK));

        System.out.println("content type: " + contentType);
        System.out.println("Headers: " + headers.toString());
        System.out.println("***************");
        System.out.println("***************");
        System.out.println(headers.get("Content-Type"));
        System.out.println(headers.get("Transfer-Encoding"));
    }

}
