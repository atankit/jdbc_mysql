package users;

import ConnectionHelper.ConnectionProvider;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTbl {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
        try{
            String q = "create table users(uId int primary key auto_increment, first_name varchar(200) not null ," +
                    " last_name varchar(200) not null, address varchar(200), gender varchar(50))";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(q);
            System.out.println("Table created...");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
