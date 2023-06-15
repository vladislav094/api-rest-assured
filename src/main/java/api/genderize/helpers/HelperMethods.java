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

//    public static void myMethod() {
////        Class<?> enclosingClass = new Object(){}.getClass().getEnclosingClass();
//        Class<?> enclosingClass = method.getClass().getEnclosingClass();
//        String className = enclosingClass.getName();
//        System.out.println("Method is called from class: " + className);
//    }

public static void main(String[] args) throws NoSuchMethodException {
//    System.out.println(isCyrillicName("влад"));
//    System.out.println(isLatinName("qwe"));
//    System.out.println(Character.UnicodeBlock.of('q'));
//    System.out.println(determineAlphabet("влада"));
//    myMethod();
}
}
