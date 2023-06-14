package api.genderize.static_data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenDebug {

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProviderMethod(){
//        return new Object[][] {{"data-one"}, {"data-two"}};
//    }
    public final static String xlsxLatinNames = "src/main/resources/gender_data/latinNames.xlsx";
    public final static String xlsxCyrillicNames = "src/main/resources/gender_data/cyrillicNames.xlsx";
    public final static String pageWithLatinMaleNames = "latinMaleNames";
    public final static String pageWithLatinFemaleNames = "latinFemaleNames";
    public final static String pageWithCyrillicMaleNames = "cyrillicMaleNames";
    public final static String pageWithCyrillicFemaleNames = "cyrillicFemaleNames";


    @DataProvider(name = "latinMaleNames")
    public Object[][] latinMaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxLatinNames, pageWithLatinMaleNames);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider(name = "latinFemaleNames")
    public Object[][] latinFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxLatinNames, pageWithLatinFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicMaleNames")
    public Object[][] cyrillicMaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxCyrillicNames, pageWithCyrillicMaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicFemaleNames")
    public Object[][] cyrillicFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(xlsxCyrillicNames, pageWithCyrillicFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }


//    @Test(dataProvider = "users")
//    public void testMethod(String param1, String param2){
//        System.out.println("id: " + param1 + ";\t" + "name: " + param2);
//    }
}
