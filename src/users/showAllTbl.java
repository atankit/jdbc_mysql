package users;

import ConnectionHelper.ConnectionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class showAllTbl {

    // show all users ----------------
    public static void showAllUsers() {
        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "select * from users";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(q);
            while (rset.next()){
                int id = rset.getInt(1);
                String fName = rset.getString(2);
                String lName = rset.getString(3);
                String address = rset.getString(4);
                String gender = rset.getString(5);

                System.out.println(id+" "+fName+" "+lName+" "+address+" "+gender);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // show user by id --------
    public static void showByUserId(){
        Connection connection = ConnectionProvider.getConnection();
        try{
            String q = "select * from users Where uId=?";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Show table by id : ");
            int id = Integer.parseInt(br.readLine());

            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setInt(1,id);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()){
                int Uid = rset.getInt(1);
                String fName = rset.getString(2);
                String lName = rset.getString(3);
                String address = rset.getString(4);
                String gender = rset.getString(5);

                System.out.println(Uid+" "+fName+" "+lName+" "+address+" "+gender);
            }


        }
        catch (IOException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
//        showAllUsers();
          showByUserId();
    }
}
