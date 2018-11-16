package DB;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConn {

    public static void main(String[] args) {
        MySQLConnectionModel connectionModel = new MySQLConnectionModel("root", "test", "localhost",3306,"gradesystem");
        Connection conn = null;
        try
        {
            conn = MySQLConnector.getConnection(connectionModel);
            if(conn!=null)
            {
                System.out.println("Connection success!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            MySQLConnector.closeResources(conn);
        }

    }
}
