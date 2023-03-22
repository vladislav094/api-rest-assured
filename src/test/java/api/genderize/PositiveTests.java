package api.genderize;

import api.genderize.genders.GenderData;
import api.genderize.genders.QueryParameters;
import api.genderize.genders.ResponseValues;
import api.genderize.specification.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PositiveTests extends Specifications {

    /*
     Identified baseURL in the public class Specifications.
     We can also create an setUp() method with an
     annotation @BeforeClass and put the baseURL there.
     Or define it in this class as shown below
    */
//    private final static String URL = "https://api.genderize.io/";

    @Test
    public void checkSuccessResultWithParameter(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertNotNull(genderData.getCount());
        Assert.assertEquals(genderData.getGender(), ResponseValues.valueGenderMale);
        Assert.assertEquals(genderData.getName(), QueryParameters.valueLatinName);
        Assert.assertNotNull(genderData.getProbability());
    }

    @Test
    public void checkResultWithCyrillicName(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueCyrillicName)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertNotNull(genderData.getCount());
        Assert.assertEquals(genderData.getGender(), ResponseValues.valueGenderMale);
        Assert.assertEquals(genderData.getName(), QueryParameters.valueCyrillicName);
        Assert.assertNotNull(genderData.getProbability());
    }

    @Test
    public void checkValueCorrespondExpectedDataForVladislavName(){
        Specifications.installSpecification(Specifications.requestSpec(), responseSpecOK200());
        GenderData genderData = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(genderData.getCount(),ResponseValues.valueCountForVladislav);
        Assert.assertEquals(genderData.getGender(), ResponseValues.valueGenderMale);
        Assert.assertEquals(genderData.getName(), ResponseValues.valueVladislav);
        Assert.assertEquals(genderData.getProbability(), ResponseValues.valueProbabilityForVladislav);
    }

    @Test
    public void checkResponseHeaders(){
        Specifications.installSpecification(requestSpec(), responseSpecOK200());
        Response response = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().response();
        String responseBodyLength = response.getBody().asString();
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
        Assert.assertTrue(Integer.parseInt(response.getHeader("x-rate-limit-remaining").trim()) > 0);
        Assert.assertEquals(responseBodyLength.length(), Integer.parseInt(response.getHeader("Content-Length").trim()));

    }

    @Test
    public void checkServerAndClientDataCorresponding(){
        /*
         This test method will be modified in the future and
         regular expressions will be used to process the current date.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        Response response = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().response();
        String serverHeaderDate = response.getHeader("Date");
        Date clientDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
        Assert.assertTrue(serverHeaderDate.contains(simpleDateFormat.format(clientDate)));
    }

    @Test
    public void checkProtocolVersion(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        Response response = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().response();
        Assert.assertTrue(response.getStatusLine().contains("HTTP/1.1"));
    }
}
