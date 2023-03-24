package api.genderize.helpers;

import api.genderize.genders.ResponseValues;

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
}
