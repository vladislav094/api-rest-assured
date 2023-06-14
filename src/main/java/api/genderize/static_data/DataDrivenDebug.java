package api.genderize.static_data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenDebug {

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProviderMethod(){
//        return new Object[][] {{"data-one"}, {"data-two"}};
//    }
    public final static String xlsxLatinNames = "src/main/resources/latinNames.xlsx";
    public final static String pageWithLatinMaleNames = "latinMaleNames";
    public final static String pageWithLatinFemaleNames = "latinFemaleNames";


    @DataProvider(name = "maleNames")
    public Object[][] dataProviderMaleData() throws Exception {
        String path = xlsxLatinNames;
        ExcelReader excelReader = new ExcelReader(path, pageWithLatinMaleNames);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider(name = "latinFemaleNames")
    public Object[][] dataProviderFemaleData() throws Exception {
        String path = xlsxLatinNames;
        ExcelReader excelReader = new ExcelReader(path, pageWithLatinFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }


//    @Test(dataProvider = "users")
//    public void testMethod(String param1, String param2){
//        System.out.println("id: " + param1 + ";\t" + "name: " + param2);
//    }
}
