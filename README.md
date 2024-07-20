# Project Overview
## Objectives
#### Design a MySQL database schema.<br>
#### Create stored procedures for CRUD operations.<br>
#### Implement triggers for automatic actions based on table events.<br>
#### Develop a Java application to interact with the database using JDBC.<br>

## Project Components
- Database Schema: Create a dummy_project database with tables for users, department, and bank details.
- Stored Procedures: Implement stored procedures for inserting, updating, and deleting users.
- Triggers: Set up triggers to automatically handle related actions, such as calculates the total salary as the sum of basic salary and bonus before inserting a new record.
- JDBC Integration: Write a Java application to call the stored procedures and handle database interactions.

## Step-by-Step Guide
### Step 1: Database Design and Setup 
#### Database Schema

```sql
  delimiter //
      create procedure CreateUsersTbl()
        begin
        create table users(
        uId int primary key auto_increment,
        first_name varchar(200) not null,
        last_name varchar(200) not null,
        address varchar(200),
        gender varchar(50))
      );
        end //

 create procedure CreateDepartmentTbl()
        begin
        create table department(
        depId int primary key auto_increment,
        department_name varchar(200),
        basic_salary int default 0,
        bonus int default 0,
        total_salary int default 0,
        acc_count int default 0,
        user_id int,
        foreign key (user_id) references users(uId)
      );
        end //

  create procedure CreateBankDetailsTbl()
      begin
      create table bankdetails(
      bId int primary key auto_increment,
      bankName varchar(200) not null,
      accountNo int not null,
      phoneNo int not null,
      depId int not null,
      foreign key (depId) references department(depId)
    );
      end //
```
### Step 2: Creating Stored Procedures
#### Stored Procedures for CRUD Operations.
```sql
 delimiter //
        create procedure InsertDepartment(
        IN dname varchar(200),
        IN basicsalary int,
        IN bonus int,
        IN userid int
          )
        begin
        insert into department(department_name, basic_salary, bonus, user_id) values (dname, basicsalary, bonus,userid);
        end //

        create  procedure updatedepart(
          IN didup int,
          IN dnameup varchar(200),
          IN basicsalaryup int,
          IN bonusup int,
          IN useridup int
        )
          begin
          Update department SET department_name = dnameup, basic_salary = basicsalaryup, bonus = bonusup , user_id = useridup  Where depId = didup;
          end //

        create procedure SelectData()
           begin
           select * from department;
           end //

        create procedure ShowDepartmentNdDelete(
           IN whereD_ID int
         )
           begin
           select department_name from  department where depId = whereD_ID;
           delete from department where depId = whereD_ID;
           end //
```
##### Select info about users and departs using INNER Join
  ```sql 
           delimiter //
      Create Procedure Select_users_depart_data()
       begin
       select u.uId, u.first_name, u.last_name, u.address, u.gender, d.department_name, d.basic_salary, d.bonus, d.total_salary, d.acc_count  from users u INNER JOIN department d ON             u.uId = d.depId;
       end //
```
### Step 3: Implementing Triggers
#### Triggers to Handle Related Actions
```sql
    create trigger before_insert_department
      before insert On department 
      for each row                  
       begin
       Set NEW.total_salary = NEW.basic_salary + NEW.bonus;  // 10k(bsalary) + 1000(bonus) = 11k total_salary
       end //

    create trigger before_update_department
       before update                   
       On department for each row
       begin
       Set NEW.total_salary = NEW.basic_salary + NEW.bonus;
       end //
```
### Step 4: Developing the Java Application
#### Java Application to Interact with MySQL

##### Database Configuration
###### 1. Database Configuration & JDBC Utility Class
   
```java
public class ConnectionProvider {
    private static Connection connection;
    public static Connection getConnection(){
        try{
            if(connection == null){
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/dummy_project",
                        "root","root");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
```
###### 2. CRUD Operations Using JDBC
 1.  Create new table (department) with the help of MySQL stored procedure
```java
public class createTblSP {
    public static void main(String[] args) {
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
```
2. Inserting data
```java
package department;
public class InsertBySP {
    public static void main(String[] args) {
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
```
3. Updating table
```java
package department;
public class UpdateTbl {
    public static void main(String[] args) {
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
```
4. Deleting table
package department;
import ConnectionHelper.ConnectionProvider;
public class DeleteDepart {
    public static void main(String[] args) {
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
```

##### Showing table
```java
package department;
public class SelectUserDepartData {
    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
         try {
            String q = "Call Select_users_depart_data()";
            CallableStatement callableStatement = connection.prepareCall(q);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
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

                System.out.println(id+" "+fName+" "+lName+" "+address+" "+gender+" "+departname+" "+basicsalary+" "+bonus+" "+totalsalary+" "+acount);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
```
###Conclusion
####This project demonstrates how to set up a MySQL database with stored procedures and triggers, and how to interact with it from a Java application using JDBC. By encapsulating the business logic within the database and leveraging JDBC for database operations, you can build robust and maintainable applications.


