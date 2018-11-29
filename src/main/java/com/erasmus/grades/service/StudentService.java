package com.erasmus.grades.service;

import com.erasmus.grades.db.MySQLConnectionModel;
import com.erasmus.grades.db.MySQLConnector;
import com.erasmus.grades.model.StudentGrade;
import com.erasmus.grades.util.ConvertGrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<StudentGrade> fetchGradesByStudentId(long id, String format) {
        MySQLConnectionModel connectionModel = new MySQLConnectionModel("root", "test", "localhost", 3306, "erasmus");
        Connection conn = null;
        try {
            conn = MySQLConnector.getConnection(connectionModel);
            if (conn != null) {
                String hql = "select u.username, utc.grade, c.name " +
                        "from User u " +
                        "inner join user_to_class as utc on u.iduser = utc.user_id " +
                        "inner join `class` as c on c.class_id = utc.class_id " +
                        "where u.iduser = ?";
                PreparedStatement p = conn.prepareStatement(hql);
                p.setLong(1, id);
                ResultSet r = p.executeQuery();

                List<StudentGrade> studentGrades = new ArrayList<>();
                while (r.next()) {
                    String username = r.getString(1);
                    int grade = r.getInt(2);
                    String name = r.getString(3);
                    if (format.equals("danish")) {
                        studentGrades.add(new StudentGrade(name, "" + grade));
                    } else {
                        String ectsGrade = ConvertGrade.convertGrade(""+grade);
                        studentGrades.add(new StudentGrade(name, ectsGrade));
                    }
                    System.out.println(username + " " + grade + " " + name);
                }

                return studentGrades;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(conn);
        }
        System.out.println("RETURN NULL");
        return null;
    }

}
