# MySQL + JDBC Mini Project
## Project Overview
This project demonstrates the integration of MySQL database concepts with a Java application using JDBC. It covers database design, stored procedures, triggers, and backend connectivity in a structured and practical way.

## Objectives
 + Design a relational MySQL database schema.
 + Implement Stored Procedures for CRUD operations.
 + Use Triggers for automated database actions.
 + Develop a Java (JDBC) application to interact with the database.

## Project Components- 

### + Database Schema-
   A database named dummy_project is created with the following tables:

   - users – Stores user details (name, salary, department, etc.)
   - department – Stores department information
   - bank_details – Stores user banking information.

###  + Stored Procedures-
    Stored procedures are implemented for:
    - Insert user
    - Update user
    - Delete user
These procedures ensure better performance, reusability, and security.
  
###  + Triggers-
    Triggers are used to automate actions:
    - Automatically calculate total salary before inserting a record.
      
### total_salary = basic_salary + bonus

This ensures data consistency without manual calculations.

### + JDBC Integration-
A Java application is developed using JDBC to:
    - Connect to MySQL database
    - Call stored procedures
    - Perform database operations (Insert, Update, Delete)
    - Handle exceptions and manage resources.

### + Key Features-
     - Structured relational database design
     - Efficient use of stored procedures
     - Automation using triggers
     - Real-time database interaction using Java

### + Learning Outcome-
  + Understanding of MySQL schema design.

  + Hands-on experience with stored procedures & triggers.

  + Practical implementation of JDBC connectivity

  + End-to-end backend workflow.

### + Conclusion
This project demonstrates how to set up a MySQL database with stored procedures and triggers, and how to interact with it from a Java application using JDBC. By encapsulating the business logic within the database and leveraging JDBC for database operations, you can build robust and maintainable applications.

