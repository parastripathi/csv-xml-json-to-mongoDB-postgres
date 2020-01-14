package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.document.EmployeeDoc;
import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.repository.EmployeeMongoRepository;
import com.training.fileskafkadb.repository.EmployeePostgresRepository;
import com.training.fileskafkadb.service.KafkaConsumerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static int index = 0;

    @Autowired
    EmployeeMongoRepository mongoRepository;

    @Autowired
    EmployeePostgresRepository postgresRepository;

    @KafkaListener(topics = "EmpReadWrite",groupId = "group-id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(Employee employee){
        System.out.println(employee);
        EmployeeDoc employeeDoc = new EmployeeDoc();
        BeanUtils.copyProperties(employee,employeeDoc);
        if(index<150)
            postgresRepository.save(employee);
        else
            mongoRepository.save(employeeDoc);

        index++;
    }
}
