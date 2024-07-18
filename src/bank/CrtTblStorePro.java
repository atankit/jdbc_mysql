package bank;

import ConnectionHelper.ConnectionProvider;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CrtTblStorePro {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();

        // Create table using stored procedure
//        CREATE PROCEDURE CreateBankDetailsTbl()
//       begin
//create table bankdetails(
//bId int primary key auto_increment,
//bankName varchar(200) not null,
//accountNo int not null,
//phoneNo int not null,
//depId int not null,
//foreign key (depId) references department(depId)
//);
//end

        // call CreateBankDetailsTbl() ------------------
       try{
           String q = "call createBankDetailsTbl()";
           CallableStatement callableStatement = connection.prepareCall(q);

           callableStatement.execute();
           System.out.println("Table created successfully using stored procedure..");
       }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
}
