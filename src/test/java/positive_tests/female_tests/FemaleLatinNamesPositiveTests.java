package positive_tests.female_tests;

import api.genderize.genders.QueryParameters;
import api.genderize.genders.pojo.GenderData;
import api.genderize.specification.Specifications;
import api.genderize.static_data.DataDrivenDebug;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FemaleLatinNamesPositiveTests {
    @Test(dataProvider = "latinFemaleNames", dataProviderClass = DataDrivenDebug.class)
    public void checkThatAllNamesIsFemale(String count ,String gender,
                                          String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        GenderData genderData = given()
                .queryParam(QueryParameters.keyName, name)
                .when()
                .get()
                .then()
                .extract().as(GenderData.class);
        Assert.assertEquals(gender, genderData.getGender());
//        Assert.assertTrue(HelperMethods.isCyrillicName(genderData.getName()));
//        Assert.assertTrue(HelperMethods.);
    }
}
