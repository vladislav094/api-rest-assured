package api.genderize;

import api.genderize.genders.GenderData;
import api.genderize.helpers.HelperData;
import api.genderize.genders.QueryParameters;
import api.genderize.genders.ResponseValues;
import api.genderize.helpers.HelperMethods;
import api.genderize.specification.Specifications;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

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
        Assert.assertTrue(genderData.isCountForVladislav(HelperData.latinName));
        Assert.assertTrue(genderData.isMale());
        Assert.assertTrue(genderData.isVladislavName(HelperData.latinName));
        Assert.assertTrue(genderData.isProbabilityForVladislav(HelperData.latinName));
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
        Assert.assertTrue(genderData.isCountForVladislav(HelperData.cyrillicName));
        Assert.assertTrue(genderData.isMale());
        Assert.assertTrue(genderData.isVladislavName(HelperData.cyrillicName));
        Assert.assertTrue(genderData.isProbabilityForVladislav(HelperData.cyrillicName));
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
        Assert.assertTrue(genderData.isCountForVladislav(HelperData.latinName));
        Assert.assertTrue(genderData.isMale());
        Assert.assertTrue(genderData.isVladislavName(HelperData.latinName));
        Assert.assertTrue(genderData.isProbabilityForVladislav(HelperData.latinName));
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
        String responseBody = response.getBody().asString();
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
        Assert.assertTrue(Integer.parseInt(response.getHeader("x-rate-limit-remaining").trim()) > 0);
        Assert.assertEquals(responseBody.length(), Integer.parseInt(response.getHeader("Content-Length").trim()));
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

//    @Test(threadPoolSize = 10, invocationCount = 200)
    @Test
    public void checkAllHeadersForRateLimiting(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        Response response = RestAssured
                .given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().response();
        Headers allHeaders = response.getHeaders();
        List <String> headersName = HelperMethods.getListAllHeaders(allHeaders);
        Assert.assertTrue(HelperMethods.allExpectedRateLimitHeaders(headersName));
        System.out.println(headersName);
    }

}
