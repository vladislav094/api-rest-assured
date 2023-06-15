package api.genderize.helpers;

import api.genderize.data_provider.DataDrivenDebug;
import api.genderize.genders.ResponseValues;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public HelperMethods() {
    }

    public static boolean allExpectedRateLimitHeaders(List<String> myObj) {
        /*
        ResponseValues.expectedXRateLimitHeaders - list with all expected headers
         */
        boolean flag = false;
        for (String head : ResponseValues.expectedXRateLimitHeaders) {
            if (myObj.contains(head)) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static List<String> getListAllHeaders(Headers myHeaders) {
        List<String> headersName = new ArrayList<>();
        for (Header header : myHeaders) {
            headersName.add(header.getName());
        }
        return headersName;
    }

    public static boolean isCyrillicName(String name) {
        return name.chars().mapToObj(c -> Character.UnicodeBlock.of(c)
                .equals(Character.UnicodeBlock.CYRILLIC)).allMatch(c -> c);
    }

    public static boolean isLatinName(String name) {
        return name.chars().mapToObj(c -> Character.UnicodeBlock.of(c)
                .equals(Character.UnicodeBlock.BASIC_LATIN)).allMatch(c -> c);
    }

    /*
    Данный метод принимает на вход в качестве аргумента другой метод.
    Извлекается название класса в котором находится метод и выполняется
    првоерка на совпадения с условиями. В зависимости от результатов
    будет возвращена определенная строка, которая соответствует таблице
    с тестовыми данными для DataProvider.
     */
    public static String defineGenderAndAlphabet(Method method) {
        if (method.getDeclaringClass().getName().toLowerCase().contains("latin") && method.getDeclaringClass().getName().toLowerCase().contains("female")) {
            return DataDrivenDebug.pageInTableWithLatinFemaleNames;
        }
        if (method.getDeclaringClass().getName().toLowerCase().contains("latin") && method.getDeclaringClass().getName().toLowerCase().contains("male")) {
            return DataDrivenDebug.pageInTableWithLatinMaleNames;
        }
        if (method.getDeclaringClass().getName().toLowerCase().contains("cyrillic") && method.getDeclaringClass().getName().toLowerCase().contains("male")) {
            return DataDrivenDebug.pageInTableWithCyrillicMaleNames;
        }
        if (method.getDeclaringClass().getName().toLowerCase().contains("cyrillic") && method.getDeclaringClass().getName().toLowerCase().contains("female")) {
            return DataDrivenDebug.pageInTableWithCyrillicFemaleNames;
        }
        return "";
    }


public static void main(String[] args){

    }
}
