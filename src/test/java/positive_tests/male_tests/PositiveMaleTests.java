package positive_tests.male_tests;

import api.genderize.genders.QueryParameters;
import api.genderize.genders.pojo.GenderData;
import api.genderize.helpers.HelperMethods;
import api.genderize.specification.Specifications;
import api.genderize.static_data.DataDrivenDebug;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PositiveMaleTests {
    @Test(dataProvider = "maleNames", dataProviderClass = DataDrivenDebug.class)
    public void checkThatAllMaleNameIsLatin(String count, String gender,
                                        String name, String probability){
        /*
        We check that all objects in the response have a gender corresponding to the passed name in the parameter. In this case, the male sex is expected.
         */
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertTrue(HelperMethods.isLatinName(genderData.getName()));
    }

    @Test(dataProvider = "maleNames", dataProviderClass = DataDrivenDebug.class)
    public void checkThatAllNamesIsMale(String count ,String gender,
                                                               String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(gender, genderData.getGender());
    }

    @Test(dataProvider = "latinFemaleNames", dataProviderClass = DataDrivenDebug.class)
    public void checkThatAllCountOfMaleNamesCorrespondTable(String count ,String gender,
                                                            String name, String probability) {
        int intCount = Double.valueOf(count).intValue();
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(intCount, genderData.getCount());
    }
}