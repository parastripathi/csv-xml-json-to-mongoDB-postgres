package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.service.ReadService;
import com.training.fileskafkadb.utilities.filehandling.CSVReadThread;
import com.training.fileskafkadb.utilities.filehandling.JSONReadThread;
import com.training.fileskafkadb.utilities.filehandling.MyList;
import com.training.fileskafkadb.utilities.filehandling.XMLReadThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadServiceImpl implements ReadService {


    @Autowired
    CSVReadThread csvReadThread;

    @Autowired
    JSONReadThread jsonReadThread;

    @Autowired
    XMLReadThread xmlReadThread;

    @Autowired
    KafkaTemplate<String,Employee> kf;

    @Override
    public void readAllFiles(){

        csvReadThread.start();

        jsonReadThread.start();

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

        List<Employee> list = MyList.getList();
        for (Employee employee : list) {
            kf.send("EmpReadWrite",employee);
        }
    }
}
