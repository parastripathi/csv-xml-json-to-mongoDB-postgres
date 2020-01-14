package com.training.fileskafkadb.utilities.kafka;

import com.training.fileskafkadb.service.impl.KafkaConsumerServiceImpl;
import com.training.fileskafkadb.utilities.filehandling.MyCollection;

public class ConsumerReadThread extends Thread {

    KafkaConsumerServiceImpl kafkaConsumerService = new KafkaConsumerServiceImpl();

    @Override
    public void run() {
        kafkaConsumerService.startConsumer();
        //MyCollection.setKafkaConsumerList(kafkaConsumerService.readList());
        kafkaConsumerService.stopConsumer();
    }
}
