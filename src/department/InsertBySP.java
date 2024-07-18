package department;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertBySP {
    public static void main(String[] args) {

//       create procedure InsertDepartment(
//        IN dname varchar(200),
//        IN basicsalary int,
//        IN bonus int,
//        IN userid int
//          )
//        begin
//        insert into department(department_name, basic_salary, bonus, user_id) values (dname, basicsalary, bonus,userid);
//        end //

        Connection connection = ConnectionProvider.getConnection();
        try{
            String q = "call InsertDepartment(?,?,?,?) ";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter department name ");
            String department = br.readLine();
            System.out.println("Enter basic salary:");
            int bsalary = Integer.parseInt(br.readLine());
            System.out.println("Enter bonus :");
            int bonus = Integer.parseInt(br.readLine());
            System.out.println("Enter user id :");
            int userid = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setString(1, department);
            callableStatement.setInt(2,bsalary);
            callableStatement.setInt(3,bonus);
            callableStatement.setInt(4,userid);

            callableStatement.execute();
            System.out.println("Data inserted ... ");
        }
        catch (IOException | SQLException e){
            System.err.println(" Department not inserted while user insert!! \n"+ e.getMessage());
        }
    }
}
