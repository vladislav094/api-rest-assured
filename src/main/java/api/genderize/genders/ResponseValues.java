package api.genderize.genders;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseValues {
    /*
    Expected data from response (body/headers)
     */
    public final static String valueGenderMale = "male";
    public final static String valueGenderFemale = "female";
    public final static Integer valueCountForVladislav = 18721;
    public final static Float valueProbabilityForVladislav = 1F;
    public final static Integer valueCountForCyrillicVladislav = 2;
    public final static Float valueProbabilityForCyrillicVladislav = 1F;
    public final static String valueVladislav = "vladislav";
    public final static String code422Description = "Unprocessable Entity";
    public final static String code429Description = "Request limit reached";
    public final static String code401Description = "Invalid API key";
    public final static String code404Description = "Not Found";
    public final static String textNotFound = "Not Found";
    public final static String textErrorMissingNameParameter = "Missing 'name' parameter";
    public final static String textInvalidNameParameter = "Invalid 'name' parameter";
    public final static List<String> expectedXRateLimitHeaders = Arrays.asList("x-rate-limit-limit",
            "x-rate-limit-remaining",
            "x-rate-limit-reset");

}
