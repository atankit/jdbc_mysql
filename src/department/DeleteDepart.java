package department;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDepart {
    public static void main(String[] args) {
//   Show department name using by id then deleted department
//        create procedure ShowNdDeleteDepart(
//    -> IN whereD_ID int
//    -> )
//    -> begin
//    -> select department_name from department where depId = whereD_ID;
//        SET foreign_key_checks = 0;
//    -> delete from department where bId = whereD_ID;
//        SET foreign_key_checks = 1;
//    -> end //

        Connection connection = ConnectionProvider.getConnection();
        try{
            String q = "Call ShowNdDeleteDepart(?)";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter deleted department Id :");
            int id = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,id);
            boolean hasResult = callableStatement.execute();
            if (hasResult){
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()){
                    String departName = resultSet.getString("department_name");

                    System.out.println(departName+" was Deleted ..");
                }
                else {
                    System.err.println("Somethings went wrong!! Please enter specified ID. ");
                }
            }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
