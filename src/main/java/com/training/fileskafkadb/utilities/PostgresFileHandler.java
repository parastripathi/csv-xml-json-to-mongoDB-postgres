package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostgresFileHandler{

    public void writeEmployee(Employee employee) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trainingdb","training","training");
        Statement statement = connection.createStatement();

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        String strDate = dateFormat.format(employee.getDateOfBirth());
        Double experience = employee.getExperience();
        //System.out.println("INSERT INTO employee (first_name , last_name , date_of_birth , experience) VALUES(" + firstName + "," + lastName + "," + strDate + "," + experience + ");");
        statement.executeUpdate("INSERT INTO employee (first_name , last_name , date_of_birth , experience) VALUES('"+ firstName + "','" + lastName + "','" + strDate + "'," + experience + ");");
        connection.close();
    }


}
