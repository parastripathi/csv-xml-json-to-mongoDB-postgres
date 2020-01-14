package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

@Service
public class PostgresFileHandler{

    public void writeEmployee(EmployeeDTO employee) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jainildb","jainil","jainil");
        Statement statement = connection.createStatement();


        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String strDate = dateFormat.format(employee.getDateOfBirth());
        Double experience = employee.getExperience();
        statement.executeUpdate("INSERT INTO employee (first_name , last_name , date_of_birth , experience) VALUES('"+ firstName + "','" + lastName + "','" + strDate + "'," + experience + ");");
        connection.close();
    }


}
