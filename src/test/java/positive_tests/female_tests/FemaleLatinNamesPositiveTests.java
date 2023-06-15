package positive_tests.female_tests;

import api.genderize.genders.QueryParameters;
import api.genderize.genders.pojo.GenderData;
import api.genderize.specification.Specifications;
import api.genderize.data_provider.DataDrivenDebug;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FemaleLatinNamesPositiveTests {
    @Test(dataProvider = "generalDataProvider", dataProviderClass = DataDrivenDebug.class)
    public void checkThatAllLatinNamesIsFemale(String count ,String gender,
                                          String name, String probability){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.oneItemInResponseSpecOK200());
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
