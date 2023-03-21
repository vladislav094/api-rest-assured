package api.genderize;

import api.genderize.genders.GenderData;
import api.genderize.genders.QueryParameters;
import io.restassured.RestAssured;

import api.genderize.specification.Specifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PositiveTests extends Specifications {

    /*
     Identified baseURL in the public class Specifications.
     We can also create an setUp() method with an
     annotation @BeforeClass and put the baseURL there.
     Or define it in this class as shown below
    */
//    private final static String URL = "https://api.genderize.io/";
    private final static String gender = "male";

    @Test
    public void checkSuccessResultWithParameter(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.latinNameVladislav)
                .when()
                .get()
                .then()
                    .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getGender(), gender);
        Assert.assertEquals(genderData.getName(), QueryParameters.latinNameVladislav);
    }

    @Test
    public void checkResultWithCyrillicName(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.cyrillicNameVladislav)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getGender(), gender);
        Assert.assertEquals(genderData.getName(), QueryParameters.cyrillicNameVladislav);
    }

    @Test
    public void checkDataTypeInTheResponseValues(){
        Specifications.installSpecification(Specifications.requestSpec(), responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.latinNameVladislav)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
    }
}
