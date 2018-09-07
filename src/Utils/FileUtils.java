package Utils;

import java.io.*;

public class FileUtils {

    private static final String FILES_LOCATION = "src/Files/";
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String INPUT = FILES_LOCATION + INPUT_FILE_NAME;

    private static final int DATE_START_INDEX = 40;
    private static final int DATE_LENGTH = 8;
    private static final int GRADE_START_INDEX = 87;
    private static final int GRADE_LENGTH = 2;

    private static int ERROR_LINE_INDEX = 101;

    public static BufferedReader openFile() {
        InputStream ins;
        Reader r = null;
        try {
            ins = new FileInputStream(INPUT);
            r = new InputStreamReader(ins, "UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            writeError(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return new BufferedReader(r);
    }

    public static void readLines(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            // System.out.println(line);
            String date = readString(line, DATE_START_INDEX, DATE_LENGTH);
            boolean isValid = DateUtils.isDateValid(date);
            String grade = readString(line, GRADE_START_INDEX, GRADE_LENGTH);
            // TODO: convert grade
            System.out.println("Date: " + date + " which is a " + (isValid ? "VALID" : "INVALID") + " date | Grade: " + grade);
        }
    }

    private static String readString(String currentLine, int position, int length) {
        int endPosition = position + length;
        return currentLine.substring(position, endPosition);
    }

    private static void writeLine(int index, String message) {
        // TODO: write line
    }

    public static void writeError(String error) {
        writeLine(ERROR_LINE_INDEX++, error);
    }

}
