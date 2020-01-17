package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
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
            MyList.add(testEmployee);
            linesRead++;
        }

        return;

    }
}