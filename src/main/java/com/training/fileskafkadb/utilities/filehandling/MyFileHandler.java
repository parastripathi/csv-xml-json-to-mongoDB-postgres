package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;

import java.io.IOException;
import java.text.ParseException;

public interface MyFileHandler {
    //void writeEmployee(Employee e) throws IOException;
    Employee readEmployee() throws IOException, ParseException;
}
