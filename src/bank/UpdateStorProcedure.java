package bank;

import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateStorProcedure {
    public static void main(String[] args) {

        // Update user by using MySql stored procedure
//        delimiter //
//        mysql> create procedure UpdateBankDet(
//   IN bId_updt int,
//In bankname_updt varchar(200),
//In acno_updt INT,
//                In phnno_upt INT,
//        In departid_updt INT
//)
//        begin
//        Update bankdetails Set bankName = bankname_updt, accountNo = acno_updt, phoneNo = phnno_upt, depId = departid_updt Where bId = bId_updt;
//        end

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call UpdateBankDet(?,?,?,?,?)";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Updated bank details Id :");
            int bid = Integer.parseInt(br.readLine());
            System.out.println("Enter new bank name : ");
            String bankname = br.readLine();
            System.out.println("Enter new account no. : ");
            int acno = Integer.parseInt(br.readLine());
            System.out.println("Enter new phone no. :");
            int phnno = Integer.parseInt(br.readLine());
            System.out.println("Enter new department id :");
            int departId = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,bid);
            callableStatement.setString(2,bankname);
            callableStatement.setInt(3,acno);
            callableStatement.setInt(4,phnno);
            callableStatement.setInt(5,departId);

            int rowsAffected =  callableStatement.executeUpdate();
            if(rowsAffected == 0) {
                System.err.println("The specified ID does not exist!! Please enter correct ID...");
            }
            else {
                System.out.println("Update successfully.");
            }
        }
        catch (IOException | SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}
