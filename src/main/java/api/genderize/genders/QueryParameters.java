package api.genderize.genders;

import java.util.Arrays;
import java.util.List;

public class QueryParameters {
    /*
    Data for query parameters
     */
    public final static String keyName = "name";
    public final static String valueLatinName ="vladislav";
    public final static String valueCyrillicName = "владислав";
    public final static String valueLatinNameCapitalLetter = "Vladislav";
    public final static String listKeyName = "name[]";
    public final static List<String> listValueWith10MaleNames = Arrays.asList("Albert", "Frank",
            "John", "Harry", "Steve", "Bob", "Michael", "Ted", "Martin", "Greg");
}
