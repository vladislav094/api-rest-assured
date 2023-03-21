package api.genderize;

import api.genderize.genders.MissingParameter;
import api.genderize.specification.Specifications;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeTests  extends Specifications {

    private final static String URL = "https://api.genderize.io";
    private final static String expectedTextError = "Missing 'name' parameter";

    @Test
    void checkWithMissingParameter(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUNIQUE(422));
        MissingParameter missingParameter = RestAssured
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), expectedTextError);
    }

    @Test
    void checkSwapKeyValue(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUNIQUE(422));
        MissingParameter missingParameter = RestAssured
                .given()
                .queryParam("vladislav", "name")
                .when()
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), expectedTextError);

    }

}
