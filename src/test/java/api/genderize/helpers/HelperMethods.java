package api.genderize.helpers;

import api.genderize.genders.ResponseValues;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
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

    public static boolean isCyrillicAlphabetName(String name){
        for (int i = 0; i < name.length(); i++) {
            char temp = name.charAt(i);
            if (!String.valueOf(Character.UnicodeBlock.of(temp)).equals("CYRILLIC")){
                return false;
            }
        }
        return true;
    }
//public static void main(String[] args) {
//    System.out.println(isCyrillicAlphabetName("влад"));
//}
}
