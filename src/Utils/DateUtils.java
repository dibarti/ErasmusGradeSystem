package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final String DANISH_DATE_FORMAT = "ddMMyyyy";

    private static boolean isDateValid(String inputDate) {
        if (inputDate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DANISH_DATE_FORMAT);

        try {
            return sdf.parse(inputDate) != null;
        } catch (ParseException e) {
            // Write Error to output
            return false;
        }
    }

}
