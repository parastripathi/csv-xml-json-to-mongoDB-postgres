package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;

import java.text.ParseException;

public class XMLReadThread extends Thread {

    @Override
    public void run() {

        Integer linesRead = 0;
        XMLFileHandler xmlHandler = null;

        xmlHandler = new XMLFileHandler();


        while(linesRead < 100) {
            assert xmlHandler != null;
            Employee testEmployee = null;
            try {
                testEmployee = xmlHandler.readEmployee();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            MyCollection.addEmployee(testEmployee);
            linesRead++;
        }

        return;

    }
}