package com.training.fileskafkadb.utilities;

//import au.com.bytecode.opencsv.CSVReader;
//import au.com.bytecode.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.training.fileskafkadb.dta.Employee;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class CSVFileHandler implements MyFileHandler{
    String fileRead = "/Users/parastripathi/Downloads/employee.csv";
    String fileWrite = "/Users/parastripathi/Downloads/new_employee.csv";
    FileReader filereader = new FileReader(fileRead);
    CSVReader csvReader = new CSVReader(filereader);
    File file = new File(fileWrite);
    public CSVFileHandler() throws IOException {
    }

    @Override
    public void writeEmployee(Employee tempEmployee) throws IOException {
        if(tempEmployee == null) return;
        FileWriter outputfile = new FileWriter(file,true);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] writeString = new String[4];
        writeString[0] = tempEmployee.getFirstName();
        writeString[1] = tempEmployee.getLastName();
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        String strDate = dateFormat.format(tempEmployee.getDateOfBirth());
        writeString[2] = strDate;
        writeString[3] = Double.toString(tempEmployee.getExperience());
        writer.writeNext(writeString);
        writer.close();
    }
    @Override
    public Employee readEmployee() throws IOException, ParseException, CsvValidationException {
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
