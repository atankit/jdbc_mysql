package AllInOne;
import ConnectionHelper.ConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateAllTbl {
    public static void main(String[] args) {
// --------------update data in three tables ---------------
//        CREATE PROCEDURE Update_all_tables(
//                IN uidup int,
//        IN fnameup varchar(200),
//                IN lnameup varchar(200),
//                IN addressup varchar(200),
//                IN genderup varchar(100),
//                IN depidup int,
//        In departnameup varchar(200),
//                IN basicsalup int,
//        IN bounsup int,
//        IN useridup int,
//        IN bankidup int,
//        IN banknup varchar(200),
//                IN accountnoup int,
//        IN phonenoup int,
//        IN departidup int
//)
//        BEGIN
//        Update users Set first_name = fnameup, last_name =lnameup , address = addressup , gender =genderup where uId = uidup;
//        update department Set department_name = departnameup, basic_salary =basicsalup ,bonus = bounsup , user_id = useridup  where depId = depidup ;
//        update bankdetails Set bankName = banknup , accountNo = accountnoup  , phoneNo =phonenoup  , depId = departidup Where bId = bankidup;
//        END //

        Connection connection = ConnectionProvider.getConnection();
        try {
            String q = "Call Update_all_tables(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter user id ");
            int uid = Integer.parseInt(br.readLine());
            System.out.println("Enter new first name : ");
            String fName = br.readLine();
            System.out.println("Enter new Last name : ");
            String lName = br.readLine();
            System.out.println("Enter new address : ");
            String address = br.readLine();
            System.out.println("Enter new gender : ");
            String gender = br.readLine();

            System.out.println("Updated department id :");
            int did = Integer.parseInt(br.readLine());
            System.out.println("Update department name :");
            String dname = br.readLine();
            System.out.println("Update basic salary:");
            int bsalary = Integer.parseInt(br.readLine());
            System.out.println("Update bouns (if any) :");
            int bonus = Integer.parseInt(br.readLine());
            System.out.println("Update user id :");
            int userid = Integer.parseInt(br.readLine());

            System.out.println("Updated bank details Id :");
            int bid = Integer.parseInt(br.readLine());
            System.out.println("Enter new bank name : ");
            String bankname = br.readLine();
            System.out.println("Enter new account no. : ");
            int acno = Integer.parseInt(br.readLine());
            System.out.println("Enter new phone no. :");
            int phnno = Integer.parseInt(br.readLine());
            System.out.println("Enter new department id :");
            int departId = Integer.parseInt(br.readLine());

            CallableStatement callableStatement = connection.prepareCall(q);
            callableStatement.setInt(1,uid);
            callableStatement.setString(2,fName);
            callableStatement.setString(3,lName);
            callableStatement.setString(4,address);
            callableStatement.setString(5,gender);

            callableStatement.setInt(6,did);
            callableStatement.setString(7,dname);
            callableStatement.setInt(8,bsalary);
            callableStatement.setInt(9,bonus);
            callableStatement.setInt(10,userid);

            callableStatement.setInt(11,bid);
            callableStatement.setString(12,bankname);
            callableStatement.setInt(13,acno);
            callableStatement.setInt(14,phnno);
            callableStatement.setInt(15,departId);

            int rowsAffected =  callableStatement.executeUpdate();
            if(rowsAffected == 1) {
                System.err.println("The specified ID does not exist!! Please enter correct ID...");
            }
            else {
                System.out.println("Update successfully.");
            }
        }
        catch (IOException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
