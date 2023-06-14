package api.genderize.static_data;
import api.genderize.helpers.HelperData;
import api.genderize.helpers.HelperMethods;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataDrivenDebug {

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProviderMethod(){
//        return new Object[][] {{"data-one"}, {"data-two"}};
//    }
    public final static String xlsxFileLatinNames = "src/main/resources/gender_data/latinNames.xlsx";
    public final static String xlsxFileCyrillicNames = "src/main/resources/gender_data/cyrillicNames.xlsx";
    public final static String pageInTableWithLatinMaleNames = "latinMaleNames";
    public final static String pageInTableWithLatinFemaleNames = "latinFemaleNames";
    public final static String pageInTableWithCyrillicMaleNames = "cyrillicMaleNames";
    public final static String pageInTableWithCyrillicFemaleNames = "cyrillicFemaleNames";


    public Object[][] debugDataProvider(Method method) throws Exception{
        String myString = Boolean.toString(HelperMethods.thatLatinMaleNames(method));
        switch ( HelperMethods.thatLatinMaleNames(method)) {

        }
    }

    @DataProvider(name = "latinMaleNames")
    public Object[][] latinMaleNamesDataProvider(Method method) throws Exception {
        System.out.println(method.getName());
        ExcelReader excelReader = new ExcelReader(xlsxFileLatinNames, pageInTableWithLatinMaleNames);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider(name = "latinFemaleNames")
    public Object[][] latinFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxFileLatinNames, pageInTableWithLatinFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicMaleNames")
    public Object[][] cyrillicMaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxFileCyrillicNames, pageInTableWithCyrillicMaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicFemaleNames")
    public Object[][] cyrillicFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxFileCyrillicNames, pageInTableWithCyrillicFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

//    public Object[][] dataProvider(){
//        return new Object[][] {{}}
//    }


//    @Test(dataProvider = "users")
//    public void testMethod(String param1, String param2){
//        System.out.println("id: " + param1 + ";\t" + "name: " + param2);
//    }
}
