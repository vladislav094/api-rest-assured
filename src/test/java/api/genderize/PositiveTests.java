package api.genderize;

import api.genderize.genders.GenderData;
import io.restassured.RestAssured;

import api.genderize.specification.Specifications;

import org.testng.Assert;
import  org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PositiveTests extends Specifications {

    private final static String URL = "https://api.genderize.io/";

    @Test
    public void checkSuccessResultWithParameter(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        String gender = "male";
        String name = "vladislav";
        GenderData genderData = RestAssured
                .given()
                .queryParam("name", "vladislav")
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getGender(), gender);
        Assert.assertEquals(genderData.getName(), name);
    }
}
