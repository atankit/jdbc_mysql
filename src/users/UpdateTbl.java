package users;

import ConnectionHelper.ConnectionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTbl {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Update users set first_name=?, last_name=?, address=?, gender=? Where uId=?";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter new first name : ");
            String fName = br.readLine();
            System.out.println("Enter new Last name : ");
            String lName = br.readLine();
            System.out.println("Enter new address : ");
            String address = br.readLine();
            System.out.println("Enter new gender : ");
            String gender = br.readLine();
            System.out.println("Enter user id ");
            int id = Integer.parseInt(br.readLine());


            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1,fName);
            pstmt.setString(2,lName);
            pstmt.setString(3,address);
            pstmt.setString(4,gender);
            pstmt.setInt(5,id);

           int rowsAffected = pstmt.executeUpdate();
           if(rowsAffected == 0) {
               System.out.println("The specified ID does not exist!! Please enter correct ID...");
           }
           else {
               System.out.println("Update successfully.");
           }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
