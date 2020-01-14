package com.training.fileskafkadb.utilities.kafka;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.service.impl.KafkaProduceServiceImpl;
import com.training.fileskafkadb.utilities.filehandling.MyCollection;

public class ProduceWriteThread extends Thread{

    KafkaProduceServiceImpl kafkaProduceService = new KafkaProduceServiceImpl();

    @Override
    public void run() {
        Employee[] employees =  MyCollection.getEmployeeList();
        kafkaProduceService.sendMessage(employees);
    }
}
