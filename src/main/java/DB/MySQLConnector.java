package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    public static Connection getConnection(MySQLConnectionModel connModel) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connModel.getConnString(), connModel.getUsername(), connModel.getPassword());
    }
    public static void closeResources(AutoCloseable closeable)
    {
        try{
            if(closeable!=null)
            {
                closeable.close();
                System.out.println(closeable.getClass().getName()+" closed !!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
