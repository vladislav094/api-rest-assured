package api.genderize.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.collection.IsMapContaining.hasKey;

public class Specifications {
    private final static String URL = "https://api.genderize.io/";
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
//                .log(LogDetail.HEADERS)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
//                .expectBody(matchesJsonSchemaInClasspath("genderResponseSchema.json"))
                .build();
    }
    public static ResponseSpecification responseSpecBadRequest400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
    public static ResponseSpecification responseSpecRedirect301(){
        return new ResponseSpecBuilder()
                .expectStatusCode(301)
                .build();
    }
    public static ResponseSpecification responseSpecUnique(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}