package department;
import ConnectionHelper.ConnectionProvider;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class createTblSP {
    public static void main(String[] args) {

//      create procedure CreateDepartmentTbl()
//    -> begin
//    ->  create table department(
//depId int primary key auto_increment,
//        department_name varchar(200),
//                basic_salary int default 0,
//                bonus int default 0,
//                total_salary int default 0,
//                acc_count int default 0,
//                user_id int,
//        foreign key (user_id) references users(uId)
//);
//        end //


       Connection connection =  ConnectionProvider.getConnection();
       try{
           String q = "call  CreateDepartmentTbl()";
           CallableStatement callableStatement = connection.prepareCall(q);

           callableStatement.execute();
           System.out.println("Table created successfully using stored procedure..");
       }
       catch (SQLException e){
           System.err.println(e.getMessage());
       }
    }
}
