package com.erasmus.grades.db;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConn {

    public static void main(String[] args) {
        MySQLConnectionModel connectionModel = new MySQLConnectionModel("root", "test", "localhost",3306,"testing");
        Connection conn = null;
        try
        {
            conn = MySQLConnector.getConnection(connectionModel);
            if(conn!=null)
            {
                System.out.println("Connection success!");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MySQLConnector.closeResources(conn);
        }

    }
}
