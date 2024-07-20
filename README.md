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

