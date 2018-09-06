package Utils;

import java.io.*;
import java.text.SimpleDateFormat;

public class FileUtils {

    private static final String FILES_LOCATION = "src/Files";
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String INPUT = FILES_LOCATION + INPUT_FILE_NAME;

    public static BufferedReader openFile() {
        InputStream ins;
        Reader r = null;
        try {
            ins = new FileInputStream(INPUT);
            r = new InputStreamReader(ins, "UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            // TODO: Write error to file
            e.printStackTrace();
        }
        return new BufferedReader(r);
    }

    public static String readLine() {
        return "";
    }

    public static String readString(String currentLine, int position, int length) {
        int endPosition = position + length;
        return currentLine.substring(position, endPosition);
    }

}
