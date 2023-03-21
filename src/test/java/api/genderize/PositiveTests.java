package api.genderize;

import api.genderize.genders.GenderData;
import api.genderize.genders.QueryParameters;
import io.restassured.RestAssured;

import api.genderize.specification.Specifications;

import org.testng.Assert;
import  org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PositiveTests extends Specifications {

    private final static String URL = "https://api.genderize.io/";
    private final static String gender = "male";

    @Test
    public void checkSuccessResultWithParameter(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
//                .queryParam("name", "vladislav")
                .queryParam(QueryParameters.name, QueryParameters.latinNameVladislav)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getGender(), gender);
        Assert.assertEquals(genderData.getName(), QueryParameters.latinNameVladislav);
    }

    @Test
    public void checkResultWithCyrillicName(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.name, QueryParameters.cyrillicNameVladislav)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getGender(), gender);
        Assert.assertEquals(genderData.getName(), QueryParameters.cyrillicNameVladislav);
    }

}
