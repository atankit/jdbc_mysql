package bank;

import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertDynamicStoredPro {
    public static void main(String[] args) {
        // Insert dynamic data using stored procedure
//        mysql> delimiter //
//        mysql> Create procedure InsertDynamicData(
//       In banknamedd varchar(200),
//       In acnodd INT,
//       In departIddd INT
//        )
//       begin
//          insert into bankdetails(bankName, accountNo, depId) values (banknamedd, acnodd, departIddd);0
//          end   //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call InsertDynamicData(?,?,?,?)";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter bank name : ");
            String bankname = br.readLine();
            System.out.println("Enter account no. : ");
            int acno = Integer.parseInt(br.readLine());
            System.out.println("Enter phone no. :");
            int phnno = Integer.parseInt(br.readLine());
            System.out.println("Enter department id :");
            int departId = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setString(1,bankname);
            callableStatement.setInt(2,acno);
            callableStatement.setInt(3,phnno);
            callableStatement.setInt(4,departId);
            callableStatement.execute();

            System.out.println("Bank details insert successfully..");
        }
        catch (SQLException | IOException e){
            System.err.println("Bank details not inserted while user insert!! \n "+e.getMessage());
        }
    }
}

