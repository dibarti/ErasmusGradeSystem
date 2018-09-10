package Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class FileUtils {

    private static final String FILES_LOCATION = "src/Files/";
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String INPUT = FILES_LOCATION + INPUT_FILE_NAME;
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private static final String OUTPUT = FILES_LOCATION + OUTPUT_FILE_NAME;

    private static final int DATE_START_INDEX = 40;
    private static final int DATE_LENGTH = 8;
    private static final int GRADE_START_INDEX = 87;
    private static final int GRADE_LENGTH = 2;

    public static BufferedReader openFile() {
        InputStream ins;
        Reader r = null;
        try {
            ins = new FileInputStream(INPUT);
            r = new InputStreamReader(ins, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            // writeError(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return new BufferedReader(r);
    }

    public static void readLines(BufferedReader br) throws IOException {
        String line;
        int currentLine = 0;
        while ((line = br.readLine()) != null) {
            processLine(line, currentLine);
            currentLine++;
        }
    }

    private static void processLine(String line, int lineNumber) {
        boolean isValidDate = validateDate(line, lineNumber);
        String newGrade = validateGrade(line, lineNumber);
    }

    private static String validateGrade(String line, int lineNumber) {
        try {
            String grade = readString(line, GRADE_START_INDEX, GRADE_LENGTH);
            return ConvertGrade.convertGrade(grade);
        } catch (ConvertGrade.NotADanishGradeException e) {
            writeError(lineNumber, line, e.getMessage());
            return null;
        }
    }

    private static boolean validateDate(String line, int lineNumber) {
        try {
            String date = readString(line, DATE_START_INDEX, DATE_LENGTH);
            return DateUtils.isDateValid(date);
        } catch (ParseException e) {
            writeError(lineNumber, line, e.getMessage());
            return false;
        }
    }

    private static String readString(String currentLine, int position, int length) {
        int endPosition = position + length;
        return currentLine.substring(position, endPosition);
    }

    private static void writeLine(int lineNumber, String line, String message) {
        // TODO: Write to output file?
        System.out.println("Write " + line + " " + message + " at Line: " + lineNumber);

    }

    public static void writeError(int lineNumber, String line, String error) {
        writeLine(lineNumber, line, "ERROR - " + error);
    }

}
