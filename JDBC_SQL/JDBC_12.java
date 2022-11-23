//package mypack;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBC_12 {
    public static void connection() {
        String empname, designation;
        int empno, age, salary;
        try {
            Scanner a = new Scanner(System.in);
            Scanner b = new Scanner(System.in);
            int i, rs, e;
            String DRIVER_CLASS = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_CLASS);
            String UID = "root";
            String PWD = "root";
            String DB_URL = "jdbc:mysql://localhost/sys";
            Connection conn = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement stmt = conn.createStatement();
            do {
                String menu = "~~~~OPERATIONS~~~~~\n 1.INSERT NEW ENTRY IN THE DATABASE\n 2.UPDATE SOME VALUE\n 3.DISPLAY\n 4.DELETE\n 5.EXIT\n ENTER YOUR OPTION : ";
                System.out.println(menu);
                String query;
                String sql = "update table employee set age=1;";
                i = a.nextInt();
                switch (i) {
                    case 1:
                        System.out
                                .println("Enter the following information to be inserted(Blank fields to be avoided)");
                        System.out.println("1.Employee number : ");
                        empno = a.nextInt();
                        System.out.println("2.Employee name : ");
                        empname = b.nextLine();
                        System.out.println("3.Age : ");
                        age = a.nextInt();
                        System.out.println("4.Designation : ");
                        designation = b.nextLine();
                        System.out.println("5.Salary : ");
                        salary = a.nextInt();
                        query = "insert into employee values(" + empno + ",'" + empname + "'," + age + ",'"
                                + designation + "'," + salary + ")";
                        rs = stmt.executeUpdate(query);
                        if (rs == 1) {
                            System.out.println("\nData inserted succesfully!!\n");
                        }
                        break;
                    case 2:
                        System.out.println("Select the field you want to update : \n1.Age\n2.Designation\n3.Salary\n");
                        int option = a.nextInt();
                        System.out.println("Enter the employee id for which you want to update data : ");
                        e = b.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("\nEnter the new age : ");
                                age = a.nextInt();
                                query = "update employee set age = " + age + " where emp_no = " + e + ";";
                                rs = stmt.executeUpdate(query);
                                if (rs == 1) {
                                    System.out.println("\nData has been updated successfully!");
                                }
                                break;
                            case 2:
                                System.out.println("\nEnter the new designation : \n");
                                designation = b.nextLine();
                                query = "update employee set designation = '" + designation + "' where emp_no =" + e
                                        + " ;";
                                rs = stmt.executeUpdate(query);
                                if (rs == 1) {
                                    System.out.println("\n Updated successfully!");
                                }
                                break;
                            case 3:
                                System.out.println("\nEnter the new salary : ");
                                salary = a.nextInt();
                                query = "update employee set salary = " + salary + " where emp_no =" + e + ";";
                                rs = stmt.executeUpdate(query);
                                if (rs == 1) {
                                    System.out.println("\n Updated successfully!");
                                }
                                break;
                            default:
                                System.out.println("\nPlease enter a valid choice\n");
                                break;
                        }
                        break;
                    case 3:
                        query = "select * from employee;";
                        ResultSet rs1 = stmt.executeQuery(query);
                        System.out.println("Emp_no\tEmp_name\tAge\tDesgntn\tSalary");
                        while (rs1.next()) {
                            empno = rs1.getInt("emp_no");
                            empname = rs1.getString("emp_name");
                            age = rs1.getInt("age");
                            designation = rs1.getString("designation");
                            salary = rs1.getInt("salary");
                            System.out
                                    .println(empno + "\t" + empname + "\t" + age + "\t" + designation + "\t" + salary);
                        }
                        break;
                    case 4:
                        System.out.println("\n1.DELETE ALL RECORDS\n2.DELETE SELECTED DATA");
                        option = a.nextInt();
                        switch (option) {
                            case 1:
                                query = "truncate table employee;";
                                rs = stmt.executeUpdate(query);
                                String query2 = "select * from employee;";
                                rs1 = stmt.executeQuery(query2);
                                if (rs1 == null)
                                    System.out.println("\nAll records have been successfully deleted");
                                break;
                            case 2:
                                System.out.println("Enter the employee id whose record you want to delete : ");
                                e = a.nextInt();
                                query = "delete from employee where emp_no = " + e + ";";
                                rs = stmt.executeUpdate(query);
                                if (rs == 1) {
                                    System.out.println("\nThe specified record has been deleted!");
                                }
                                String query1 = "select * from employee;";
                                rs1 = stmt.executeQuery(query1);
                                System.out.println("Emp_no\tEmp_name\tAge\tDesgntn\tSalary");
                                while (rs1.next()) {
                                    empno = rs1.getInt("emp_no");
                                    empname = rs1.getString("emp_name");
                                    age = rs1.getInt("age");
                                    designation = rs1.getString("designation");
                                    salary = rs1.getInt("salary");
                                    System.out.println(
                                            empno + "\t" + empname + "\t" + age + "\t" + designation + "\t" + salary);
                                }
                                break;
                        }
                    case 5:
                        System.exit(0);
                }
            } while (i <= 5);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connection();
    }
}
