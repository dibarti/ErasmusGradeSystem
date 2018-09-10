package Utils;

public class ConvertGrade {

    private static final int[] DANISH_GRADE = {12, 10, 7, 4, 2, 0, -3};
    private static final String[] ECTS_GRADE = {"A", "B", "C", "D", "E", "Fx", "F"};

    public static String convertGrade(String input) throws NotADanishGradeException {
        int number = isNumber(input);
        return isDanishGrade(number);
    }

    private static int isNumber(String input) throws NumberFormatException, NullPointerException {
        return Integer.parseInt(input);
    }

    private static String isDanishGrade(int number) throws NotADanishGradeException {

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
}
