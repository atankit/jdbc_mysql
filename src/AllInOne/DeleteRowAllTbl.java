package AllInOne;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRowAllTbl {
    public static void main(String[] args) {

        // MySql stored procedure to delete records from User, Department and BankDetails related tables that use
        // foreign keys.
//        CREATE PROCEDURE Delete_rowAll_tables(
//         IN same_id int               -- all three tables must be same id's.
//        )
//        begin
//        select u.uId, u.first_name, u.last_name, d.department_name, b.phoneNo
//        from users u INNER JOIN department d ON u.uId = d.depId INNER JOIN bankdetails b ON d.depId = b.depId
//        Where u.uId = same_id;        -- msg
//
//        SET foreign_key_checks = 0;    -- to prevent constraint issur during deletion.
//        Delete From users Where uId =same_id;
//        Delete From department Where depId = same_id;
//        Delete From bankdetails Where bId = same_id;
//        SET foreign_key_checks = 1;
//        end //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call Delete_rowAll_tables(?) ";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter id :");
            String sameId = br.readLine();

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setString(1,sameId);
            boolean hasResult = callableStatement.execute();
            if (hasResult) {
                ResultSet resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    int userid = resultSet.getInt("uId");
                    String fname = resultSet.getString("first_name");
                    String lname = resultSet.getString("last_name");
                    String department = resultSet.getString("department_name");
                    int phone = resultSet.getInt("phoneNo");

                    System.out.println(userid+" "+fname+" "+lname+" "+department+" "+phone+" " + "Record deleted..");
                }
            }
            else {
                System.out.println("User, department and bank details row deleted");
            }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
