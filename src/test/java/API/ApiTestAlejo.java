package API;

import API.model.User;
import API.model.UserResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
public class ApiTestAlejo {
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
    public void createUserTestAsAResponse(){

        User user = new User();
        user.setName("morpheus");
        user.setJob("leader");

        Response response = given()
                .when()
                .body(user)
                .post("users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        UserResponse userResponse = response.as(UserResponse.class);

        System.out.println("ID: " + userResponse.getId());
        System.out.println("Name: " + userResponse.getName());
        System.out.println("Job: " + userResponse.getJob());
        System.out.println("Created At: " + userResponse.getCreatedAt());

    }

    @Test
    public void createUserTestAsPojo(){

        User user = new User();
        user.setName("morpheus");
        user.setJob("leader");

        UserResponse userResponse  = given()
                .when()
                .body(user)
                .post("users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(UserResponse.class);


        System.out.println("ID: " + userResponse.getId());
        System.out.println("Name: " + userResponse.getName());
        System.out.println("Job: " + userResponse.getJob());
        System.out.println("Created At: " + userResponse.getCreatedAt());

    }


}
