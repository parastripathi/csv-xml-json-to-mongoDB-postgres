package com.training.fileskafkadb.utilities;

import com.training.fileskafkadb.dta.Employee;

import java.net.UnknownHostException;

public class MongoWriteThread extends Thread {


    @Override
    public void run() {

        Integer linesWritten = 0;

        MongoFileHandler mongoFileHandler = null;

        try {
            mongoFileHandler = new MongoFileHandler();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        while (linesWritten < 200) {
            Employee testEmployee = MyCollection.getEmployee();

            try {
                mongoFileHandler.writeEmployee(testEmployee);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            linesWritten++;
        }

        return;


    }

}
