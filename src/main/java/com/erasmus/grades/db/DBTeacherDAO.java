package com.erasmus.grades.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBTeacherDAO extends MySQLConnector {

    private PreparedStatement getCoursesTaughtStmt;

    public DBTeacherDAO() {
    }

    //TEACHER METHODS

    public ResultSet getCoursesTaught(int userID) {
        ResultSet resultSet = null;
        try {
            con = getConnection(connectionModel);
            getCoursesTaughtStmt = con.prepareCall("CALL testing.getCoursesTaught(?)");
            getCoursesTaughtStmt.setInt(1, userID);
            resultSet = getCoursesTaughtStmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCourseActivities(int courseId) {
        ResultSet resultSet = null;
        try {
            con = getConnection(connectionModel);
            getCoursesTaughtStmt = con.prepareCall("CALL testing.getActivitiesCourse(?)");
            getCoursesTaughtStmt.setInt(1, courseId);
            resultSet = getCoursesTaughtStmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getStudentsByActivityId(int activityId) {
        ResultSet resultSet = null;
        try {
            con = getConnection(connectionModel);
            getCoursesTaughtStmt = con.prepareCall("CALL testing.getActivityStudents(?)");
            getCoursesTaughtStmt.setInt(1, activityId);
            resultSet = getCoursesTaughtStmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int updateGrade(int studentID, int activityID, int grade) {
        try {
            con = getConnection(connectionModel);
            PreparedStatement updateGradeStmt = con.prepareCall("CALL testing.updateGrade(?, ?, ?)");
            updateGradeStmt.setInt(1, activityID);
            updateGradeStmt.setInt(2, studentID);
            updateGradeStmt.setInt(3, grade);
            return updateGradeStmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources(con);
        }
        return 0;
    }

}



