package com.erasmus.Utils;

import com.erasmus.grades.util.ConvertGrade;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertGradeTest {

    @Test
    public void maxScoreConversion() {
        String input = "12";
        String ectsGrade = ConvertGrade.convertGrade(input);
        assertEquals("Conversion not right", "A", ectsGrade);
    }

    @Test
    public void notADanishGrade() {
        String ectsGrade = null;
        String input = "11";
        ectsGrade = ConvertGrade.convertGrade(input);
        assertNull("ECTS grade should be null", ectsGrade);
    }

    @Test
    public void examEctsGradePassed() {
        String ectsGrade = "E";
        boolean passed = ConvertGrade.examPassed(ectsGrade);
        assertTrue(String.format("ECTS grade: %s should pass the exam", ectsGrade), passed);
    }

    @Test
    public void examEctsGradeFailed() {
        String ectsGrade = "F";
        boolean failed = ConvertGrade.examPassed(ectsGrade);
        assertFalse(String.format("ECTS grade: %s should fail the exam", ectsGrade), failed);
    }

    @Test
    public void examDanishGradePassed() {
        int danishGrade = 2;
        boolean passed = ConvertGrade.examPassed(danishGrade);
        assertTrue(String.format("Danish grade: %d should pass the exam", danishGrade), passed);
    }

    @Test
    public void examDanishGradeFailed() {
        int danishGrade = 0;
        boolean failed = ConvertGrade.examPassed(danishGrade);
        assertFalse(String.format("Danish grade: %d should fail the exam", danishGrade), failed);
    }

}
