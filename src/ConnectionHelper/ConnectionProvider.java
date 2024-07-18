package ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;
    public static Connection getConnection(){
        try{
            if(connection == null){
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/dummy_project",
                        "root","root");

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
