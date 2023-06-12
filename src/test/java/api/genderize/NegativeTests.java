package api.genderize;

import api.genderize.genders.*;
import api.genderize.specification.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        /*
        We check the error text in the response body for a request without parametersю
        ResponseValues.textErrorMissingNameParameter = "Missing 'name' parameter"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUnique(422));
        MissingParameter missingParameter = RestAssured
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), ResponseValues.textErrorMissingNameParameter);
    }

    @Test
    void checkStatusLineWithMissingParameters(){
        /*
        Checking the description of the response error code for a request without parameters. In this case, a description is expected for code 422.
        ResponseValues.code422Description = "Unprocessable Entity"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUnique(422));
        Response response = RestAssured
                .given()
                .get()
                .then()
                .extract().response();
        Assert.assertTrue(response.getStatusLine().contains(ResponseValues.code422Description));
    }

    @Test
    void checkMissingValueInParameter(){
        /*
        We check the structure of the object in the response body for the request without specifying a value.
         */
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
        /*
        We check the error text in the message body for the request in which the key and value are rearranged.
        ResponseValues.textErrorMissingNameParameter = "Missing 'name' parameter"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUnique(422));
        MissingParameter missingParameter = RestAssured
                .given()
                .queryParam(QueryParameters.valueLatinName, QueryParameters.keyName)
                .when()
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), ResponseValues.textErrorMissingNameParameter);
    }

    @Test
    public void checkThatMore10NamesCannotPassed(){
        /*
        We check the error text in the message body when trying to pass more than 10 parameters in the request.
        QueryParameters.listValueWith10MaleNames - list with 10 male names.
        ResponseValues.textInvalidNameParameter - "Invalid 'name' parameter"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUnique(422));
        List<String> listValueWith11Name = new ArrayList<>(QueryParameters.listValueWith10MaleNames);
        listValueWith11Name.add("Random");
        MissingParameter missingParameter = RestAssured
                .given()
                .queryParams(QueryParameters.listKeyName, listValueWith11Name)
                .when()
                .get()
                .then()
                .extract().as(MissingParameter.class);
        Assert.assertEquals(missingParameter.getError(), ResponseValues.textInvalidNameParameter);
    }

    @Test
    public void checkResultWithSpaceBetweenLettersInParameter(){
        /*
        We check the structure of the response body when trying to pass a value with a space between characters.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, "Spa ce")
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getCount(), 0);
        Assert.assertNull(genderData.getGender());
        Assert.assertTrue(genderData.getName().contains(" "));
        Assert.assertEquals(genderData.getProbability(), 0);
    }

    @Test
    public void checkExecutePOSTMethodInsteadOfGET(){
        /*
        Checking an attempt to execute an unsupported POST method for this router.
        ResponseValues.code404Description = "Not Found"
        ResponseValues.textNotFound = "Not Found"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecUnique(404));
        Response response = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .post()
                .then()
                .extract().response();
        Assert.assertTrue(response.getStatusLine().contains(ResponseValues.code404Description));
        Assert.assertTrue(response.getBody().asString().contains(ResponseValues.textNotFound));
    }

}
