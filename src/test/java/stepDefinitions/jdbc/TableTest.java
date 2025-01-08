package stepDefinitions.jdbc;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableTest {

    private List<String> actualEmployeeNames = new ArrayList<>();

    @Given("I query all employee names from the {string} table")
    public void iQueryAllEmployeeNamesFromTheTable(String name) {

        String url = "jdbc:mariadb://3.138.139.2/my_jdbc_db";
        String user = "admin";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database");

            String query = "SELECT name FROM `" + name + "`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Employee Data: ");

            while (resultSet.next()) {
                actualEmployeeNames.add(resultSet.getString("name"));
            }

            System.out.println("Actual Employee Names: " + actualEmployeeNames);

            if (actualEmployeeNames.isEmpty()) {
                System.out.println("No employee names found in the table: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("I should get the following names:")
    public void iShouldGetTheFollowingNames(DataTable dataTable) {
        List<String> expectedNames = dataTable.asList();
        System.out.println("Employee Names: " + expectedNames);

        if (actualEmployeeNames.size() == expectedNames.size()) {
            System.out.println("The sizes of the lists match.");

            Collections.sort(actualEmployeeNames);
            Collections.sort(expectedNames);

            if (actualEmployeeNames.equals(expectedNames)) {
                System.out.println("The employee names from the DB are match the expected names");
            } else {
                System.out.println("The employee names do not match the expected names.");
                System.out.println("Sorted Actual Names: " + actualEmployeeNames);
                System.out.println("Sorted Expected Names: " + expectedNames);
            }
        }
    }
}


