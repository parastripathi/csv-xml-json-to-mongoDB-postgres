package com.training.fileskafkadb.utilities;


import com.training.fileskafkadb.dta.Employee;

public class JSONReadThread extends Thread {

    @Override
    public void run() {

        Integer linesRead = 0;
        JSONFileHandler jsonHandler = null;

            jsonHandler = new JSONFileHandler();


            while(linesRead < 100) {
                assert jsonHandler != null;
                Employee testEmployee = jsonHandler.readEmployee();
                MyCollection.addEmployee(testEmployee);
                linesRead++;
            }

            return;

    }
}
