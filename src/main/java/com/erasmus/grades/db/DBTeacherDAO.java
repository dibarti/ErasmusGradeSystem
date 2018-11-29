package com.erasmus.grades.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBTeacherDAO extends MySQLConnector{

    private PreparedStatement getCoursesTaughtStmt;
    private PreparedStatement getStudentsCourseStmt;
    private PreparedStatement updateGradeStmt;

    public DBTeacherDAO(String ip, String usr, String pass, String db, int port) throws SQLException {

        super.connection(ip, usr, pass, db, port);
        try {

            getCoursesTaughtStmt = con.prepareCall("CALL testingDB.getCoursesTaught(?)");
            getStudentsCourseStmt = con.prepareCall("CALL testingDB.getStudentsCourse(?)");
            updateGradeStmt = con.prepareCall("CALL testingDB.updateGrade(?, ?)");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

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

    public ResultSet updateGrade(int studentID, int activityID) {
        ResultSet resultSet = null;
        try {
            updateGradeStmt.setInt(1, activityID);
            updateGradeStmt.setInt(2, studentID);
            resultSet = updateGradeStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}



