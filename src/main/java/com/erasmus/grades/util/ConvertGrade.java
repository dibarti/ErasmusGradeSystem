package com.erasmus.grades.util;

import java.util.Arrays;

public class ConvertGrade {

    private static final int[] DANISH_GRADE = {12, 10, 7, 4, 2, 0, -3};
    private static final String[] ECTS_GRADE = {"A", "B", "C", "D", "E", "Fx", "F"};

    public static String convertGrade(String input) throws NotADanishGradeException {
        int number = isNumber(input);
        return getEctsGrade(number);
    }

    private static int isNumber(String input) throws NumberFormatException, NullPointerException {
        return Integer.parseInt(input);
    }

    private static String getEctsGrade(int number) throws NotADanishGradeException {
        for (int i = 0; i < DANISH_GRADE.length; i++) {
            if (DANISH_GRADE[i] == number) {
                return ECTS_GRADE[i];
            }
        }
        throw new NotADanishGradeException("Number: " + number + " is not a Danish grade");
    }

    public static class NotADanishGradeException extends Exception {
        NotADanishGradeException(String message) { super(message); }
    }

    public static boolean examPassed(int danishGrade) {
        try {
            getEctsGrade(danishGrade);
            // Only the grades 12, 10, 7, 4, 2 should pass the exam
            int passedGradesLength = 5;
            int[] passedArray = Arrays.copyOf(DANISH_GRADE, passedGradesLength);
            for (int grade : passedArray) {
                if (grade == danishGrade) {
                    return true;
                }
            }
        } catch (NotADanishGradeException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean examPassed(String ectsGrade) {
        // Only the grades A, B, C, D, E should pass the exam
        int passedGradesLength = 5;
        String[] passedArray = Arrays.copyOf(ECTS_GRADE, passedGradesLength);
        for (String grade : passedArray) {
            if (grade.equals(ectsGrade)) {
                return true;
            }
        }
        return false;
    }
}
