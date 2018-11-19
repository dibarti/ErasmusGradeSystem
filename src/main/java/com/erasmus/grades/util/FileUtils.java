package com.erasmus.grades.util;

import com.erasmus.grades.model.Line;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String FILES_LOCATION = "src/main/java/com.erasmus.Files/";
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String INPUT = FILES_LOCATION + INPUT_FILE_NAME;
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private static final String OUTPUT = FILES_LOCATION + OUTPUT_FILE_NAME;

    private static final int DATE_START_INDEX = 40;
    private static final int DATE_LENGTH = 8;
    private static final int GRADE_START_INDEX = 87;
    private static final int GRADE_LENGTH = 2;

    private static List<Line> lines = new ArrayList<>();

    public static BufferedReader openFile() {
        InputStream ins;
        Reader r = null;
        try {
            ins = new FileInputStream(INPUT);
            r = new InputStreamReader(ins, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new BufferedReader(r);
    }

    public static List<Line> readLines(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String date = readString(line, DATE_START_INDEX, DATE_LENGTH);
            String grade = readString(line, GRADE_START_INDEX, GRADE_LENGTH);
            lines.add(new Line(date, grade));
        }
        return lines;
    }

    public static void writeLines(List<Line> lines) {
        try (PrintWriter pw = new PrintWriter(OUTPUT)) {
            pw.println("DATE     | GRADE | ERROR");
            for (Line line : lines) {
                pw.println(line.toString());
            }
            System.out.println("Write to file complete!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String readString(String currentLine, int position, int length) {
        int endPosition = position + length;
        return currentLine.substring(position, endPosition);
    }

}
