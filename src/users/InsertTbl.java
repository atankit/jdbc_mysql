package users;

import ConnectionHelper.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTbl {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
//         Static value -------
        try{
            String q = "insert into users(first_name, last_name, address, gender) " +
                    "values (?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1,"Deepanshu");
            pstmt.setString(2,"Bisht");
            pstmt.setString(3,"Chandighar");
            pstmt.setString(4,"Male");

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully..");
            connection.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
