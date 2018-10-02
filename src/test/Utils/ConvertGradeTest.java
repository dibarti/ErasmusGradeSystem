package Utils;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class ConvertGradeTest {

    @Test
    public void maxScoreConversion() {
        String ectsGrade = null;
        String input = "12";
        try {
            ectsGrade = ConvertGrade.convertGrade(input);
        } catch (ConvertGrade.NotADanishGradeException e) {
            e.printStackTrace();
        }
        assertEquals("Conversion not right", "A", ectsGrade);
    }

    @Test
    public void notADanishGrade() {
        String ectsGrade = null;
        String input = "11";
        try {
            ectsGrade = ConvertGrade.convertGrade(input);
        } catch (ConvertGrade.NotADanishGradeException e) {
            assertNotNull("Exception should be thrown", e);
        } finally {
            assertNull("ECTS grade should be null", ectsGrade);
        }
    }

}
