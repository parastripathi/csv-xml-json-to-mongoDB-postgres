package com.training.fileskafkadb.utilities.filehandling;


import com.training.fileskafkadb.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JSONReadThread extends Thread {

    @Override
    public void run() {

        Integer linesRead = 0;
        JSONFileHandler jsonHandler;

            jsonHandler = new JSONFileHandler();

            while(linesRead < 100) {
                assert jsonHandler != null;
                Employee testEmployee = jsonHandler.readEmployee();
                MyList.add(testEmployee);
                linesRead++;
            }

            return;

    }
}
