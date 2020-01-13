package com.training.fileskafkadb.utilities;

import com.opencsv.exceptions.CsvValidationException;
import com.training.fileskafkadb.dta.Employee;

import java.io.IOException;
import java.text.ParseException;

public interface MyFileHandler {
    void writeEmployee(Employee e) throws IOException;
    Employee readEmployee() throws IOException, ParseException, CsvValidationException;
}
