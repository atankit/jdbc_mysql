package department;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserByDepart {
    public static void main(String[] args) {
//       -- Search user and department data by department_name
//        Create Procedure Select_user_by_department(
//    -> IN departSr varchar(200)
//    -> )
//    -> begin
//                ->  select u.uId, u.first_name, u.last_name, u.address, u.gender, d.department_name, d.basic_salary, d.bonus, d.total_salary, d.acc_count  from users u INNER JOIN department d ON u.uId = d.depId where department_name = departSr;
//    -> end //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call Select_user_by_department(?)";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Search by department name :");
            String depart_name = bufferedReader.readLine();

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setString(1, depart_name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String departname = resultSet.getString(6);
                int basicsalary = resultSet.getInt(7);
                int bonus = resultSet.getInt(8);
                int totalsalary = resultSet.getInt(9);
                int acount = resultSet.getInt(10);

                System.out.println(id + " " + fName + " " + lName + " " + address + " " + gender + " " + departname + " " + basicsalary + " " + bonus + " " + totalsalary + " " + acount);

            }
        } catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
