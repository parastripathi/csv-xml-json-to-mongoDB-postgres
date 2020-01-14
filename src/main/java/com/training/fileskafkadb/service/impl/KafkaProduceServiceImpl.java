package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.service.KafkaProducerService;
import com.training.fileskafkadb.utilities.kafka.KafkaService;

public class KafkaProduceServiceImpl implements KafkaProducerService {
    KafkaService kf=new KafkaService();

    public void sendMessage(Employee[] employees){
        for (int index = 0;index<employees.length;index++){
            kf.send(employees[index]);
        }
        System.out.println("successfully published...");
    }
}
