package com.erasmus.grades.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    private final static String url = "jdbc:mysql://";

    protected Connection con;
    protected MySQLConnectionModel connectionModel = new MySQLConnectionModel("root", "test", "localhost", 3306, "testing");

    public static Connection getConnection(MySQLConnectionModel connModel) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connModel.getConnString(), connModel.getUsername(), connModel.getPassword());
    }

    public void connection(String ip, String usr, String pass, String db, int port) throws SQLException {
        String aux = url + ip + ":" + port + "/" + db;

        con = DriverManager.getConnection(aux, usr, pass);
        System.out.println("Establishing connection");
    }

    public static void closeResources(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
                System.out.println(closeable.getClass().getName() + " closed !!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
