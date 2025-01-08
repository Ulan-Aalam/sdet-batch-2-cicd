package stepDefinitions.jdbc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTesting {
    @Given("Establish new connection")
    public void establish_new_connection() {
        String url = "jdbc:mariadb://18.224.14.144/my_jdbc_db";
        String user = "admin";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database");

            Statement statement = connection.createStatement();//Object is used to execute SQL query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");// fetch the all rows and columns

            System.out.println("Employee Data:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");

                System.out.println("ID: " + id);
                System.out.println("name: " + name);
                System.out.println("department: " + department);
                System.out.println("salary: " + salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

