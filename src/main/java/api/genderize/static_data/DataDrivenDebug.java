package api.genderize.static_data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenDebug {

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProviderMethod(){
//        return new Object[][] {{"data-one"}, {"data-two"}};
//    }

    @DataProvider(name = "maleNames")
    public Object[][] dataProviderUsers() throws Exception {
        String path = "src/main/resources/menName.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider(name = "femaleNames")
    public Object[][] dataProviderUsers2() throws Exception {
        String path = "src/main/resources/menName.xlsx";
        ExcelReader excelReader = new ExcelReader(path, "list-2");
        return excelReader.getCustomSheetDataForTDD();
    }


    @Test(dataProvider = "users")
    public void testMethod(String param1, String param2){
        System.out.println("id: " + param1 + ";\t" + "name: " + param2);
    }
}
