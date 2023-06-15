package api.genderize.assertions;

import org.testng.Assert;

public class Assertions extends Assert {
    public static void assertTrue(boolean condition) {
        assertTrue(condition, (String)null);
        System.out.println(condition);
    }
}
