package com.training.fileskafkadb.utilities.filehandling;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.utilities.kafkaconfig.KafkaService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class CSVReadThread extends Thread {

    KafkaService kf=new KafkaService();
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
                kf.send(testEmployee);
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
