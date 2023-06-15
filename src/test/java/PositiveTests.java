//import api.genderize.genders.GenderChecking;
import api.genderize.genders.QueryParameters;
import api.genderize.helpers.HelperData;
import api.genderize.helpers.HelperMethods;
import api.genderize.genders.pojo.GenderData;
import api.genderize.specification.Specifications;
import api.genderize.data_provider.DataDrivenDebug;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class PositiveTests{

    /*
     Identified baseURL in the public class Specifications.
     We can also create an setUp() method with an
     annotation @BeforeClass and put the baseURL there.
     Or define it in this class as shown below
    */

    @Test
    public void checkSuccessDetermineGenderData(){
        /*
        Checking the response body for valid data. We check that the data is coming, the values correspond to the expected
        QueryParameters.keyName = "name"
        QueryParameters.valueLatinName = "vladislav"
        HelperData.latinName = "latin"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
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
        /*
        We check the results in the response body when determining gender by name in Cyrillic characters.
        QueryParameters.keyName = "name"
        QueryParameters.valueCyrillicName = "владислав"
        HelperData.cyrillicName = "cyrillic"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueCyrillicName)
                .when()
                .get()
                .then()
                .extract().body().as(GenderData.class);
        Assert.assertTrue(HelperMethods.isCyrillicName(genderData.getName()));
//        Assert.assertTrue(genderData.isCountForVladislav(HelperData.cyrillicName));
//        Assert.assertTrue(genderData.getGender().isMale());
//        Assert.assertTrue(genderData.isVladislavName(HelperData.cyrillicName));
//        Assert.assertTrue(genderData.isProbabilityForVladislav(HelperData.cyrillicName));
    }

    @Test(dataProvider = "users", dataProviderClass = DataDrivenDebug.class)
    public void checkValueCorrespondExpectedDataForVladislavName(String id, String name){
        /*
        Checking the data in the response body for a static name "vladislav".
        QueryParameters.keyName = "name"
        QueryParameters.valueLatinName = "vladislav"
        HelperData.latinName = "latin"
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertTrue(HelperMethods.isLatinName(genderData.getName()));
//        Assert.assertTrue(genderData.isMale());
//        Assert.assertTrue(genderData.isVladislavName(HelperData.latinName));
//        Assert.assertTrue(genderData.isProbabilityForVladislav(HelperData.latinName));
    }

    @Test
    public void checkResponseHeaders(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        /*
        We check that the response headers correspond to the expected results.
         */
        Response response = given()
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
         We check that the date in the response headers corresponds to the current date on the user's computer.
         This test method will be modified in the future and
         regular expressions will be used to process the current date.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        Response response = given()
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
        /*
        We check that the protocol used and its version correspond to the expected
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        Response response = given()
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
        /*
        We check that the response contains headers that are responsible for the speed limit and the allowed number of requests.
        HelperMethods.allExpectedRateLimitHeaders -a method that accepts a list of headers from the response as input and checks for a Rate-Limiting-Headers.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        Response response = given()
                .queryParam(QueryParameters.keyName, QueryParameters.valueLatinName)
                .when()
                .get()
                .then()
                .extract().response();
        Headers allHeaders = response.getHeaders();
        List <String> headersName = HelperMethods.getListAllHeaders(allHeaders);
        Assert.assertTrue(HelperMethods.allExpectedRateLimitHeaders(headersName));
    }

    @Test
    public void check10NameWereTransferredInParameters(){
        /*
        We check that the number of objects in the response body corresponds to the specified number of parameters in the request.
        QueryParameters.listValueWith10MaleNames - list with 10 male names.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        List<GenderData> genderData = given()
                .queryParams(QueryParameters.listKeyName, QueryParameters.listValueWith10MaleNames)
                .when()
                .get()
                .then()
                .extract().body().jsonPath().get();
        Assert.assertEquals(genderData.size(), 10);
    }
}
