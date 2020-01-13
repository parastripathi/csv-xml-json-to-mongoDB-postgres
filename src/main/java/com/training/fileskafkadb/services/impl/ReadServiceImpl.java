package com.training.fileskafkadb.services.impl;

import com.training.fileskafkadb.services.ReadService;
import com.training.fileskafkadb.utilities.CSVReadThread;
import com.training.fileskafkadb.utilities.JSONReadThread;
import com.training.fileskafkadb.utilities.XMLReadThread;
import org.springframework.stereotype.Service;

@Service
public class ReadServiceImpl implements ReadService {
    @Override
    public void readAllFiles() {

        CSVReadThread csvReadThread = new CSVReadThread();
        csvReadThread.start();


        JSONReadThread jsonReadThread = new JSONReadThread();
        jsonReadThread.start();

        XMLReadThread xmlReadThread = new XMLReadThread();
        xmlReadThread.start();

        try {
            csvReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            jsonReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            xmlReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
