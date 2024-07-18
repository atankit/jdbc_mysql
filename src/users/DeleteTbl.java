package users;

import ConnectionHelper.ConnectionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DeleteTbl {
    public static void main(String[] args) {

        Connection connection = ConnectionProvider.getConnection();
        try{
           String q = "select first_name, last_name from users where uId=?";
           String q1 = "delete from users Where uId=?";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Delete user id : ");
            int id = Integer.parseInt(br.readLine());

           PreparedStatement pstmt = connection.prepareStatement(q);
           PreparedStatement pstmt1 = connection.prepareStatement(q1);

           // fetch user first & last name -------
            pstmt.setInt(1,id);
            ResultSet rset = pstmt.executeQuery();
            if(rset.next()){
                String fname = rset.getString("first_name");
                String lname = rset.getString("last_name");

                // delete same user ---------
                pstmt1.setInt(1,id);
                int affected = pstmt1.executeUpdate();
                if(affected > 0) {
                    System.out.println(fname +" " + lname + " was deleted account... ");
                }
            }
            else {
                System.err.println("User not found with the given Id !!");
            }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
