package Utils;

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
            e.printStackTrace();
        }
    }
}