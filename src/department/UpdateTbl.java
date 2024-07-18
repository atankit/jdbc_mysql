package department;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateTbl {
    public static void main(String[] args) {

//        create  procedure updatedepart(
//                IN didup int,
//        IN dnameup varchar(200),
//                IN basicsalaryup int,
//        IN bonusup int,
//        IN useridup int
//)
//        begin
//        Update department SET department_name = dnameup, basic_salary = basicsalaryup, bonus = bonusup , user_id = useridup  Where depId = didup;
//        end //


        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "call updatedepart(?,?,?,?,?)";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Updated department id :");
            int id = Integer.parseInt(br.readLine());
            System.out.println("Update department name :");
            String dname = br.readLine();
            System.out.println("Update basic salary:");
            int bsalary = Integer.parseInt(br.readLine());
            System.out.println("Update bouns (if any) :");
            int bonus = Integer.parseInt(br.readLine());
            System.out.println("Update user id :");
            int userid = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,id);
            callableStatement.setString(2,dname);
            callableStatement.setInt(3,bsalary);
            callableStatement.setInt(4,bonus);
            callableStatement.setInt(5,userid);

            int rowsAffected =  callableStatement.executeUpdate();
            if(rowsAffected == 0) {
                System.err.println("The specified ID does not exist!! Please enter correct ID...");
            }
            else {
                System.out.println("Update successfully.");
            }
        }
        catch (SQLException | IOException e){
            System.out.println(e.getMessage());
        }

    }
}
