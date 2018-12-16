package com.erasmus.grades.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBTeacherDAO extends MySQLConnector {

    private PreparedStatement getCoursesTaughtStmt;
    private PreparedStatement getStudentsCourseStmt;
    private PreparedStatement updateGradeStmt;

    public DBTeacherDAO() {
    }

    public DBTeacherDAO(String ip, String usr, String pass, String db, int port) throws SQLException {

        super.connection(ip, usr, pass, db, port);
        try {

            getCoursesTaughtStmt = con.prepareCall("CALL testing.getCoursesTaught(?)");
            getStudentsCourseStmt = con.prepareCall("CALL testing.getStudentsCourse(?)");
            updateGradeStmt = con.prepareCall("CALL testing.updateGrade(?, ?, ?)");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

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

    public int updateGrade(int studentID, int activityID, int grade) {
//        ResultSet resultSet = null;
        try {
            con = getConnection(connectionModel);
            updateGradeStmt = con.prepareCall("CALL testing.updateGrade(?, ?, ?)");
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
//        return resultSet;
    }

}



