package com.training.fileskafkadb.utilities.filehandling;


import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.utilities.kafka.KafkaService;
import org.springframework.stereotype.Service;

@Service
public class JSONReadThread extends Thread {

    KafkaService kf=new KafkaService();
    @Override
    public void run() {

        Integer linesRead = 0;
        JSONFileHandler jsonHandler;

            jsonHandler = new JSONFileHandler();

            while(linesRead < 100) {
                assert jsonHandler != null;
                Employee testEmployee = jsonHandler.readEmployee();
                kf.send(testEmployee);
                //MyCollection.addEmployee(testEmployee);
                linesRead++;
            }

            return;

    }
}
