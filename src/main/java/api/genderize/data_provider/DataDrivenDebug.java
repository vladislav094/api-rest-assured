package api.genderize.data_provider;
import api.genderize.helpers.HelperMethods;
import org.testng.annotations.DataProvider;

import javax.annotation.CheckForNull;
import java.lang.reflect.Method;

public class DataDrivenDebug {

    public final static String XMLFileLatinNames = "src/main/resources/gender_data/latinNames.xlsx";
    public final static String XMLFileCyrillicNames = "src/main/resources/gender_data/cyrillicNames.xlsx";
    public final static String pageInTableWithLatinMaleNames = "latinMaleNames";
    public final static String pageInTableWithLatinFemaleNames = "latinFemaleNames";
    public final static String pageInTableWithCyrillicMaleNames = "cyrillicMaleNames";
    public final static String pageInTableWithCyrillicFemaleNames = "cyrillicFemaleNames";


    /*
    Для корректной работы метода "generalDataProvider" необходимо, чтобы
    тестовый метод содержал в своём названии необходимый алфавит и гендер.
    Имя методо считывается и выполняется проверка на наличие совпадения
    возможных вариантов. По результатам будет выполнен тест кейс с данными
    из соответствующей таблицы и страницы с данными
     */
    @CheckForNull
    @DataProvider(name = "generalDataProvider")
    public Object[][] generalDataProvider(Method method) throws Exception{
        ExcelReader excelReader = null;
        switch (HelperMethods.defineGenderAndAlphabet(method)){
            case pageInTableWithLatinMaleNames:
                excelReader = new ExcelReader(XMLFileLatinNames, pageInTableWithLatinMaleNames);
                return excelReader.getSheetDataForTDD();
            case pageInTableWithLatinFemaleNames:
                excelReader = new ExcelReader(XMLFileLatinNames, pageInTableWithLatinFemaleNames);
                return excelReader.getCustomSheetDataForTDD();
            case pageInTableWithCyrillicMaleNames:
                excelReader = new ExcelReader(XMLFileCyrillicNames, pageInTableWithCyrillicMaleNames);
                return excelReader.getCustomSheetDataForTDD();
            case pageInTableWithCyrillicFemaleNames:
                 excelReader = new ExcelReader(XMLFileCyrillicNames, pageInTableWithCyrillicFemaleNames);
                return excelReader.getCustomSheetDataForTDD();
            default: return new Object[][] {excelReader.getSheetDataForTDD()};
        }
    }

    @DataProvider(name = "latinMaleNames")
    public Object[][] latinMaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(XMLFileLatinNames, pageInTableWithLatinMaleNames);
        return excelReader.getSheetDataForTDD();
    }

    @DataProvider(name = "latinFemaleNames")
    public Object[][] latinFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(XMLFileLatinNames, pageInTableWithLatinFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicMaleNames")
    public Object[][] cyrillicMaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(XMLFileCyrillicNames, pageInTableWithCyrillicMaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

    @DataProvider(name = "cyrillicFemaleNames")
    public Object[][] cyrillicFemaleNamesDataProvider() throws Exception {
        ExcelReader excelReader = new ExcelReader(XMLFileCyrillicNames, pageInTableWithCyrillicFemaleNames);
        return excelReader.getCustomSheetDataForTDD();
    }

}
