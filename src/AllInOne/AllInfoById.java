package AllInOne;

import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllInfoById {
    public static void main(String[] args) {

//        Show All info by user id
//      create procedure AllInfoUserId(
//    -> In userid INT
//    -> )
//    -> begin
//    -> select u.uId, u.first_name, u.last_name, u.address, u.gender,
//    -> d.department_name, d.basic_salary, d.total_salary,
//    ->   b.bankName, b.accountNo, b.phoneNo
//    -> from users u INNER JOIN department d ON u.uId = d.depId INNER JOIN bankdetails b ON d.depId = b.depId
//    ->  Where u.uId = userid;
//    -> end //

        Connection connection = ConnectionProvider.getConnection();
        try{
            String q = " Call AllInfoUserId(?)";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Show user by ID :");
            int user_id = Integer.parseInt(br.readLine());
            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,user_id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                int userid = resultSet.getInt(1);
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String departname = resultSet.getString(6);
                int basicsalary = resultSet.getInt(7);
                int totalsalary = resultSet.getInt(8);
                String bankName = resultSet.getString(9);
                int acNo = resultSet.getInt(10);
                int phnno = resultSet.getInt(11);

                System.out.println(userid+" "+fName+" "+lName+" "+address+" "+gender+" "+departname+" "+basicsalary+" "+
                        totalsalary+" "+bankName+" "+acNo+" "+phnno);
            }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
