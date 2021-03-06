package com.erasmus.Utils;

import com.erasmus.grades.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void isDateValid() {
        String validDate = "20180908";
        try {
            assertTrue(DateUtils.isDateValid(validDate));
        } catch (ParseException e) {
            assertNull("Exception should not be thrown", e);
        }
    }

    @Test
    public void isInvalidDateValid() {
        String validDate = "20183008";
        try {
            assertFalse(DateUtils.isDateValid(validDate));
        } catch (ParseException e) {
            assertNotNull("Exception should be thrown", e);
        }
    }
}