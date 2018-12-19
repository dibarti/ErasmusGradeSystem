package com.erasmus.grades.service;

import com.erasmus.grades.dao.DBTeacherDAO;
import com.erasmus.grades.db.MySQLConnector;
import com.erasmus.grades.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Activity> fetchActivitiesByCourseId(int id) {
        DBTeacherDAO teacherDAO = new DBTeacherDAO();
        ResultSet rs = teacherDAO.getCourseActivities(id);
        List<Activity> activities = new ArrayList<>();
        try {
            while (rs.next()) {
                activities.add(getActivity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(rs);
        }
        return activities;
    }

    private Activity getActivity(ResultSet rs) throws SQLException {
        int activityId = rs.getInt("ID");
        String activityName = rs.getString("activityName");
        String activityType = rs.getString("activity");

        System.out.println(activityId + " " + activityName + " " + activityType);

        return new Activity(activityId, activityName, activityType);
    }


    public List<ActivityStudent> fetchStudentsByActivityId(int id) {
        DBTeacherDAO teacherDAO = new DBTeacherDAO();
        ResultSet rs = teacherDAO.getStudentsByActivityId(id);
        List<ActivityStudent> activityStudents = new ArrayList<>();
        try {
            while (rs.next()) {
                activityStudents.add(getActivityStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(rs);
        }
        return activityStudents;
    }

    private ActivityStudent getActivityStudent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");

        Student student = new Student(id, name, surname, email);

        Integer grade = (Integer) rs.getObject("grade");

        System.out.println(id + " " + name + " " + surname + " " + email + " " + grade);

        return new ActivityStudent(student, grade);
    }

    public boolean updateGrade(UpdateGradeRequest request) {
        DBTeacherDAO teacherDAO = new DBTeacherDAO();
        int count = teacherDAO.updateGrade(request.getStudentId(), request.getActivityId(), request.getGrade());

        return count > 0;
    }

}
