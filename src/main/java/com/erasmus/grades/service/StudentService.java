package com.erasmus.grades.service;

import com.erasmus.grades.db.DBStudentDAO;
import com.erasmus.grades.db.MySQLConnector;
import com.erasmus.grades.model.StudentGrade;
import com.erasmus.grades.util.ConvertGrade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentService {

    private static StudentService instance;

    private StudentService() {
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public List<StudentGrade> fetchGradesByStudentId(int id, String format) {
        DBStudentDAO studentDAO = new DBStudentDAO();
        ResultSet rs = studentDAO.getActivitiesStudent(id);
        List<StudentGrade> studentGrades = new ArrayList<>();
        try {
            while (rs.next()) {
                studentGrades.add(getGrade(format, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(rs);
        }
        return studentGrades;
    }

    private StudentGrade getGrade(String format, ResultSet rs) throws SQLException {
        String activityName = rs.getString("activityName");
        String activityType = rs.getString("activity");
        int grade = rs.getInt("grade");
        int weight = rs.getInt("weight");
        Date dateDone = rs.getDate("dateDone");

        System.out.println(activityName + " " + grade + " " + dateDone);

        if (format.equals("danish")) {
            return new StudentGrade(activityName, activityType, "" + grade, weight, dateDone);
        } else {
            String ectsGrade = ConvertGrade.convertGrade("" + grade);
            return new StudentGrade(activityName, activityType, ectsGrade, weight, dateDone);
        }
    }

}
