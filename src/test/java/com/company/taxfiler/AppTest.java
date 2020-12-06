package com.company.taxfiler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    //@Test
    void testCase() {
 
        Assertions.fail("not found good reason to pass");
        Assertions.fail(AppTest::message);
        Assertions.assertEquals("", "");
    }
     
    private static String message () {
        return "not found good reason to pass";
    }
}