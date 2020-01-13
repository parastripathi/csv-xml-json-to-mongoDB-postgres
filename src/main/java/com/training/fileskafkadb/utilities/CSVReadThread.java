package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;

import java.io.IOException;
import java.text.ParseException;

public class CSVReadThread extends Thread {

    @Override
    public void run() {

        Integer linesRead = 0;
        CSVFileHandler csvHandler = null;
        try {
            csvHandler = new CSVFileHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while(linesRead < 100) {
                assert csvHandler != null;
                Employee testEmployee = csvHandler.readEmployee();
                MyCollection.addEmployee(testEmployee);
                linesRead++;
            }

            return;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
