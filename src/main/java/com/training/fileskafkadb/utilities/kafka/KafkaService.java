package com.training.fileskafkadb.utilities.kafka;

import com.training.fileskafkadb.entity.Employee;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaService {
    private final Producer<String,Employee> kafkaProducer;
    private final Consumer<String, Employee> consumer ;


    public KafkaService() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "group-id");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");



        kafkaProducer =new KafkaProducer<String, Employee>(
                props,
                new StringSerializer(),
                new KafkaJsonSerializer()
        );

        consumer =
                new KafkaConsumer<String, Employee>(props, new StringDeserializer() ,new KafkaJsonDeserializer<Employee>(Employee.class));
    }


    public void send(Employee employee) {
        kafkaProducer.send(new ProducerRecord<>("EmpReadWrite", employee.getFirstName(),  employee));

    }

    public Consumer consume(String topic){
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

}
