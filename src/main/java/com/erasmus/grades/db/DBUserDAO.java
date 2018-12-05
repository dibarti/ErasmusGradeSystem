package com.erasmus.grades.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUserDAO extends MySQLConnector{

    private PreparedStatement getCoursesTaughtStmt;
    private PreparedStatement getStudentsCourseStmt;
    private PreparedStatement updateGradeStmt;
    private PreparedStatement getActivitiesStudentCourseStmt;
    private PreparedStatement getCoursesStudentStmt;

    public DBUserDAO(String ip, String usr, String pass, String db, int port) throws SQLException {

        super.connection(ip, usr, pass, db, port);
        try {

            getCoursesTaughtStmt = con.prepareCall("CALL testingDB.getCoursesTaught(?)");
            getStudentsCourseStmt = con.prepareCall("CALL testingDB.getStudentsCourse(?)");
            updateGradeStmt = con.prepareCall("CALL testingDB.updateGrade(?, ?, ?)");
            getActivitiesStudentCourseStmt = con.prepareCall("CALL testingDB.getActivitiesStudentCourse(?, ?)");
            getCoursesStudentStmt = con.prepareCall("CALL testingDB.getCoursesStudent(?)");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    //TEACHER METHODS

    public ResultSet getCoursesTaught(int userID) {
        ResultSet resultSet = null;
        try {
            getCoursesTaughtStmt.setInt(1, userID);
            resultSet = getCoursesTaughtStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getStudentsCourse(int id) {
        ResultSet resultSet = null;
        try {
            getStudentsCourseStmt.setInt(1, id);
            resultSet = getStudentsCourseStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet updateGrade(int studentID, int activityID, int grade) {
        ResultSet resultSet = null;
        try {
            updateGradeStmt.setInt(1, activityID);
            updateGradeStmt.setInt(2, studentID);
            updateGradeStmt.setInt(3, grade);
            resultSet = updateGradeStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    // STUDENT METHODS

    public ResultSet getActivitiesStudentCourse(int userID, int courseID) {
        ResultSet resultSet = null;
        try {
            getActivitiesStudentCourseStmt.setInt(1, userID);
            getActivitiesStudentCourseStmt.setInt(2, courseID);
            resultSet = getActivitiesStudentCourseStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getCoursesStudent(int id) {
        ResultSet resultSet = null;
        try {
            getCoursesStudentStmt.setInt(1, id);
            resultSet = getActivitiesStudentCourseStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}



