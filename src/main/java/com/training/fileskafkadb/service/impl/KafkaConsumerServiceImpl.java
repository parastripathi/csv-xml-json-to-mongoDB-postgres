package com.training.fileskafkadb.service.impl;

import com.training.fileskafkadb.entity.Employee;
import com.training.fileskafkadb.dto.EmployeeDTO;
import com.training.fileskafkadb.repository.EmployeeMongoRepository;
import com.training.fileskafkadb.repository.EmployeePostgresRepository;
import com.training.fileskafkadb.service.KafkaConsumerService;
import com.training.fileskafkadb.utilities.kafka.KafkaService;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static int index = 0;
    KafkaService kf = new KafkaService();

    Consumer<String, EmployeeDTO> consumer;

    @Autowired
    EmployeeMongoRepository mongoRepository;

    @Autowired
    EmployeePostgresRepository postgresRepository;

    public void startConsumer(){
        consumer = kf.consume("EmpReadWrite");
    }

    @KafkaListener(topics = "EmpReadWrite",groupId = "group-id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(Employee employee){
        /*Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);*/
        System.out.println(index);
        if(index<150)
            postgresRepository.save(employee);
        else
            mongoRepository.save(employee);

        index++;

        //mongoRepository.save(employee);
        /*synchronized (this){
            System.out.println(employee);
            MyCollection.addEmployee(employee);
        }*/

        /*while (true) {
            final ConsumerRecords<String, Employee> consumerRecords = consumer.poll(1);
            for (ConsumerRecord<String, Employee> records : consumerRecords) {
                if (records.value()!=null)
                    return records.value();
            }
        }*/
    }

    public void stopConsumer(){
        consumer.close();
    }

    //@KafkaListener(topics = "test", groupId = "group_id")
    /*public void startConsume() {


        final Consumer<String, Employee> consumer = kf.consume("EmpReadWrite");

        final int giveUp = 350;
        int noRecordsCount = 0;

        while (noRecordsCount <= 300) {
            final ConsumerRecords<String, Employee> consumerRecords =
                    consumer.poll(1000);
            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            consumerRecords.forEach(record -> {
                list.add(record.value());
                //System.out.println(record.value());

            });
            consumer.commitAsync();
        }
        System.out.println(list);
        consumer.close();
    }*/
}
