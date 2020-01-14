package com.training.fileskafkadb.utilities.filehandling;

import au.com.bytecode.opencsv.CSVReader;
import com.training.fileskafkadb.entity.Employee;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CSVFileHandler implements MyFileHandler {
    String fileRead = "/Users/jainilpatel/Desktop/Java Code Snippets/employee.csv";
    String fileWrite = "/Users/jainilpatel/Desktop/Java Code Snippets/employeeNew.csv";
    FileReader filereader = new FileReader(fileRead);
    CSVReader csvReader = new CSVReader(filereader);
    File file = new File(fileWrite);
    public CSVFileHandler() throws IOException {
    }

    @Override
    public Employee readEmployee() throws IOException, ParseException {
        Object[] nextRecord;
        Employee newEmployee = new Employee();
        if ((nextRecord = csvReader.readNext()) == null) return null;
        newEmployee.setFirstName((String) nextRecord[0]);
        newEmployee.setLastName((String) nextRecord[1]);
        newEmployee.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse((String) nextRecord[2]));
        newEmployee.setExperience((new Double(nextRecord[3].toString())));
        return newEmployee;
    }
    public void finalize() throws IOException {
        csvReader.close();
    }


}
