package positive_tests.male_tests;

import api.genderize.data_provider.DataProviderDriven;
import api.genderize.genders.QueryParameters;
import api.genderize.genders.pojo.GenderData;
import api.genderize.helpers.HelperMethods;
import api.genderize.specification.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MaleLatinNamesPositiveTests {
    @Test(dataProvider = "generalDataProvider", dataProviderClass = DataProviderDriven.class)
    public void checkThatAllMaleNameIsLatin(String count, String gender,
                                        String name, String probability){
        /*
        We check that all objects in the response have a gender corresponding to the passed name in the parameter. In this case, the male sex is expected.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertTrue(HelperMethods.isLatinName(genderData.getName()));
    }

    @Test(dataProvider = "generalDataProvider", dataProviderClass = DataProviderDriven.class)
    public void checkThatEachValueForCountOfMaleLatinNamesCorrespondTable(String count ,String gender,
                                                                     String name, String probability) {
        int intCount = Double.valueOf(count).intValue();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(intCount, genderData.getCount());
    }

    @Test(dataProvider = "generalDataProvider", dataProviderClass = DataProviderDriven.class)
    public void checkSuccessDetermineGenderDataForMaleLatinNames(String count ,String gender,
                                                               String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(gender, genderData.getGender());
    }
    @Test(dataProvider = "generalDataProvider", dataProviderClass = DataProviderDriven.class)
    public void checkThatEachOfMaleLatinNameInResponseDisplayCorrectly(String count, String gender,
                                               String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(name, genderData.getName());
    }

    public void checkThatEachValueForProbabilityOfMaleLatinNamesCorrespondTable(String count, String gender,
                                                                                String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(name, genderData.getProbability());

    }

}
