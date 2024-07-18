package users;

import ConnectionHelper.ConnectionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDynamic {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
        // Dynamic values
        try{
            String q = "insert into users(first_name, last_name, address, gender) " +
                    "values (?,?,?,?)";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter First name : ");
            String fName = br.readLine();
            System.out.println("Enter Last name : ");
            String lName = br.readLine();
            System.out.println("Enter address : ");
            String address = br.readLine();
            System.out.println("Enter gender : ");
            String gender = br.readLine();

            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1,fName);
            pstmt.setString(2,lName);
            pstmt.setString(3,address);
            pstmt.setString(4,gender);

            pstmt.executeUpdate();
            System.out.println("data inserted successfully...");
            connection.close();
        }
        catch (IOException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
