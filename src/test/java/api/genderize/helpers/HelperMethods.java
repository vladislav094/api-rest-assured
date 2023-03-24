package api.genderize.helpers;

import api.genderize.genders.ResponseValues;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static boolean allExpectedRateLimitHeaders(List<String> myObj){
        boolean flag = false;
        for(String head : ResponseValues.ExpectedXRateLimitHeaders){
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

}
