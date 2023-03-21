package api.genderize;

import api.genderize.genders.MissingParameter;
import api.genderize.specification.Specifications;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeTests  extends Specifications {

    private final static String URL = "https://api.genderize.io";

    @Test
    void checkWithMissingParameter(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUNIQUE(422));
        String expectedResult = "Missing 'name' parameter";
        MissingParameter missingParameter = RestAssured
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), expectedResult);
    }


}
