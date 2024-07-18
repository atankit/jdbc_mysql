package bank;

import ConnectionHelper.ConnectionProvider;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowBankDetailsProcedure {
    public static void main(String[] args) {
// Display all bank details using stored procedure
//        create procedure ShowAllBankDet()
//    -> begin
//                -> select * from bankdetails;
//    -> end //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call ShowAllBankDet()";

            CallableStatement callableStatement = connection.prepareCall(q);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                int bankId = resultSet.getInt(1);
                String bankName = resultSet.getString(2);
                int acNo = resultSet.getInt(3);
                int phnno = resultSet.getInt(4);
                int userId = resultSet.getInt(5);

                System.out.println(bankId+" "+bankName+" "+acNo+" "+phnno+" "+userId );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
