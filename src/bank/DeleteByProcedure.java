package bank;

import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteByProcedure {
    public static void main(String[] args) {

//   Show bank name and account no. using by id then deleted account
//        create procedure ShowBankNdDelete(
//    -> IN whereB_ID int
//    -> )
//    -> begin
//                -> select bankName, accountNo from  dummy_project.bankdetails where bId = whereB_ID;
//    -> delete from bankdetails where bId = whereB_ID;
//    -> end //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call  ShowBankNdDelete(?)";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter deleted bank details Id :");
            int bid = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,bid);

           boolean hasResult = callableStatement.execute();
           if (hasResult){
               ResultSet resultSet = callableStatement.getResultSet();
               if(resultSet.next()){
                   String BankName = resultSet.getString("bankName");
                   int AcNo = resultSet.getInt("accountNo");

                   System.out.println(BankName+" "+AcNo +" Account Deleted ..");
               }
               else {
                   System.err.println("Somethings went wrong!! Please enter specified ID. ");
               }
           }
        }
        catch (IOException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
