package com.erasmus.grades.service;

import com.erasmus.grades.db.DBTeacherDAO;
import com.erasmus.grades.db.MySQLConnector;
import com.erasmus.grades.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherService {

    private static TeacherService instance;

    private TeacherService() {
    }

    public static TeacherService getInstance() {
        if (instance == null) {
            instance = new TeacherService();
        }
        return instance;
    }

    public List<Course> fetchCoursesByTeacherId(int id) {
        DBTeacherDAO teacherDAO = new DBTeacherDAO();
        ResultSet rs = teacherDAO.getCoursesTaught(id);
        List<Course> teacherCourses = new ArrayList<>();
        try {
            while (rs.next()) {
                teacherCourses.add(getTeacherCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(rs);
        }
        return teacherCourses;
    }

    private Course getTeacherCourse(ResultSet rs) throws SQLException {
        int courseId = rs.getInt("courseId");
        String courseName = rs.getString("courseName");
        int yearDone = rs.getInt("yearDone");

        System.out.println(courseId + " " + courseName + " " + yearDone);

        return new Course(courseId, courseName, yearDone);
    }

}
