package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final String DANISH_DATE_FORMAT = "yyyyMMdd";

    public static boolean isDateValid(String inputDate) throws ParseException {
        if (inputDate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DANISH_DATE_FORMAT);
        sdf.setLenient(false);

        return sdf.parse(inputDate) != null;
    }

}
