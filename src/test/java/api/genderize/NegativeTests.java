package api.genderize;

import api.genderize.genders.MissingParameter;
import api.genderize.genders.QueryParameters;
import api.genderize.genders.ResponseValues;
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
        System.out.println(response.getStatusLine());
        Assert.assertTrue(response.getStatusLine().contains(ResponseValues.code422Description));
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
