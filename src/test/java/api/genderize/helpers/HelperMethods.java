package api.genderize.helpers;

import api.genderize.genders.ResponseValues;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperMethods {

    public static boolean allExpectedRateLimitHeaders(List<String> myObj){
        /*
        ResponseValues.expectedXRateLimitHeaders - list with all expected headers
         */
        boolean flag = false;
        for(String head : ResponseValues.expectedXRateLimitHeaders){
            if (myObj.contains(head)){
                flag = true;
            }
            else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static List<String> getListAllHeaders(Headers myHeaders){
        List<String> headersName = new ArrayList<>();
        for(Header header: myHeaders){
            headersName.add(header.getName());
        }
        return headersName;
    }

    public static String determineAlphabet(String alphabet) {
        String result = "";
        List<Character> arrayCharacters = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            char temp = alphabet.charAt(i);
            arrayCharacters.add(temp);
        }
        arrayCharacters.forEach(x-> Assert.assertEquals(String.valueOf(Character.UnicodeBlock.of(x)), Character.UnicodeBlock.CYRILLIC));

        return result;
    }

    public static boolean isCyrillicName(String name){
        List<Character> arrayCharacters = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            char temp = name.charAt(i);
            arrayCharacters.add(temp);
        }
        arrayCharacters.forEach(x-> Assert.assertEquals(Character.UnicodeBlock.of(x), Character.UnicodeBlock.CYRILLIC));
        return true;
    }
//    public static boolean isLatinName(String name) {
//        for ()
//    }

//public static void main(String[] args) {
//    System.out.println(isCyrillicName("влад"));
//    System.out.println(Character.UnicodeBlock.of('q'));
//    System.out.println(determineAlphabet("влада"));
//}
}
