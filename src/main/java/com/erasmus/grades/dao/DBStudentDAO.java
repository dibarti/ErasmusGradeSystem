package com.erasmus.grades.dao;

import com.erasmus.grades.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBStudentDAO extends MySQLConnector {

    public DBStudentDAO() {
    }

    public ResultSet getActivitiesStudent(int userID) {
        ResultSet resultSet = null;
        try {
            con = getConnection(connectionModel);
            PreparedStatement getActivitiesStudentCourseStmt = con.prepareCall("CALL testing.getActivitiesStudentCourse(?)");
            getActivitiesStudentCourseStmt.setInt(1, userID);
            resultSet = getActivitiesStudentCourseStmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}



