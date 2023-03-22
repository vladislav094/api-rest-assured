package api.genderize;

import api.genderize.genders.*;
import api.genderize.specification.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests extends Specifications {

    /*
     Identified baseURL in the public class Specifications.
     We can also create an setUp() method with an
     annotation @BeforeClass and put the baseURL there.
     Or define it in this class as shown below
    */
//    private final static String URL = "https://api.genderize.io";


    @Test
    void checkErrorTextWithMissingParameters(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUNIQUE(422));
        MissingParameter missingParameter = RestAssured
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), ResponseValues.textErrorMissingNameParameter);
    }

    @Test
    void checkStatusLineWithMissingParameters(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUNIQUE(422));
        Response response = RestAssured
                .given()
                .get()
                .then()
                .extract().response();
        Assert.assertTrue(response.getStatusLine().contains(ResponseValues.code422Description));
    }

    @Test
    void checkMissingValueInParameter(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName)
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals((int) genderData.getCount(), 0);
        Assert.assertNull(genderData.getGender());
        Assert.assertEquals(genderData.getName().length(), 0);
        Assert.assertEquals((float) genderData.getProbability(), 0);
    }

    @Test
    void checkSwapKeyValue(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUNIQUE(422));
        MissingParameter missingParameter = RestAssured
                .given()
                .queryParam("vladislav", "name")
                .when()
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), ResponseValues.textErrorMissingNameParameter);
    }
}
