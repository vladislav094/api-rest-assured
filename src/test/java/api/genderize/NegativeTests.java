package api.genderize;

import api.genderize.genders.MissingParameter;
import api.genderize.specification.Specifications;
import io.restassured.RestAssured;
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
    private final static String expectedTextError = "Missing 'name' parameter";

    @Test
    void checkWithMissingParameter(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUNIQUE(422));
        MissingParameter missingParameter = RestAssured
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), expectedTextError);
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
        Assert.assertEquals(missingParameter.getError(), expectedTextError);
    }

}
