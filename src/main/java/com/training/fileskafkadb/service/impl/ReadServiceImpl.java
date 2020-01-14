package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.service.ReadService;
import com.training.fileskafkadb.utilities.filehandling.CSVReadThread;
import com.training.fileskafkadb.utilities.filehandling.JSONReadThread;
import com.training.fileskafkadb.utilities.filehandling.XMLReadThread;
import org.springframework.stereotype.Service;

@Service
public class ReadServiceImpl implements ReadService {

    @Override
    public void readAllFiles(){

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
